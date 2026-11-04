def call(String image) {
    sh "docker build -t ${image} Jenkins/Lab21/DemoApp"
    sh "trivy image --exit-code 0 --severity HIGH,CRITICAL ${image}"
    withCredentials([usernamePassword(credentialsId: 'dockerhub',
            usernameVariable: 'USR', passwordVariable: 'PSW')]) {
        sh 'echo "$PSW" | docker login -u "$USR" --password-stdin'
        sh "docker push ${image}"
    }
    sh "docker rmi ${image}"
}
