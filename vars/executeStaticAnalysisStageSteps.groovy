#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis] stage steps...")

    /*
    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'TOKEN')]) {
        withSonarQubeEnv('sonarqube') {
            sh("mvn -Dsonar.host.url=http://$SONARQUBE_SERVICE_HOST:$SONARQUBE_SERVICE_PORT -Dsonar.login=$TOKEN -s dynamic-settings.xml -e sonar:sonar")
        }
    }
    */

    withSonarQubeEnv('sonarqube') {
        sh("mvn -s dynamic-settings.xml -e sonar:sonar")
    }

    timeout(time: 45, unit: 'SECONDS') {
        waitForQualityGate(abortPipeline: true)
    }

    echo("Completed [Static Analysis] stage steps.")
}
