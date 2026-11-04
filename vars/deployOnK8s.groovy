def call(String image) {
    sh "sed -i 's|image: abdalrahmanx/ivolve-demoapp:.*|image: ${image}|' Jenkins/Lab21/k8s.yaml"
    sh "grep image: Jenkins/Lab21/k8s.yaml"
    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KC')]) {
        sh 'kubectl --kubeconfig $KC -n ivolve apply -f Jenkins/Lab21/k8s.yaml'
    }
}
