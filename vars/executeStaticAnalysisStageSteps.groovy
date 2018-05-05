#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis] stage steps...")

    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'TOKEN')]) {
        sh '''
          set +x
          mvn -Dsonar.host.url=http://$SONARQUBE_SERVICE_HOST:$SONARQUBE_SERVICE_PORT -Dsonar.login=$TOKEN -s dynamic-settings.xml -e sonar:sonar
        '''
    }

    echo("Completed [Static Analysis] stage steps.")
}
