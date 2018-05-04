#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests and Static Analysis] stage steps...")

    def branches = [:]

    branches['test'] = {sh("mvn -e -Dmaven.main.skip=true -Dmaven.test.skip=true test")}

    branches['static-analysis'] = {sh("mvn -e -Dsonar.host.url=http://${SONARQUBE_SERVICE_HOST}:${SONARQUBE_SERVICE_PORT} -Dsonar.login=${SONARQUBE_SERVICE_LOGIN_TOKEN} sonar:sonar")}

    parallel branches

    echo("Completed [Unit Tests and Static Analysis] stage steps.")
}
