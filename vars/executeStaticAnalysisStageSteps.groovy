#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis] stage steps...")

    def SONARQUBE_SERVICE_LOGIN_TOKEN = null
    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'TOKEN')])
        SONARQUBE_SERVICE_LOGIN_TOKEN = "${TOKEN}"
    }

    sh("mvn -Dsonar.host.url=http://${SONARQUBE_SERVICE_HOST}:${SONARQUBE_SERVICE_PORT} -Dsonar.login=${SONARQUBE_SERVICE_LOGIN_TOKEN} -s dynamic-settings.xml -e sonar:sonar")

    echo("Completed [Static Analysis] stage steps.")
}
