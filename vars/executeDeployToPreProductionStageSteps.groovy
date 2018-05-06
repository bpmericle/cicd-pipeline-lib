#!/usr/bin/env groovy

def call() {
    echo("Executing [Deploy to Pre-Production] stage steps...")

    withKubeConfig([credentialsId: 'docker-cloud', serverUrl: "${KUBERNETES_SERVICE_HOST}"]) {
        sh('kubectl get pods')
    }

    echo("Completed [Deploy to Pre-Production] stage steps.")
}
