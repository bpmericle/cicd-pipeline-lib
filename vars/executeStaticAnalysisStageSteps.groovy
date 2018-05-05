#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis] stage steps...")

    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'TOKEN')]) {
        sh '''
          set +x
          mvn -Dsonar.host.url=http://$SONARQUBE_SERVICE_HOST:$SONARQUBE_SERVICE_PORT -Dsonar.login=$TOKEN -s dynamic-settings.xml -e sonar:sonar
        '''
    }

    timeout(time: 45, unit: 'SECONDS') { 
        def qg = waitForQualityGate()
        if (qg.status != 'OK') {
            error "Pipeline aborted due to static analysis quality gate failure: ${qg.status}"
        } else {
            echo("Static analysis quality gate passed.")
        }
    }

    echo("Completed [Static Analysis] stage steps.")
}
