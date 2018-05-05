#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish to Artifact Repository] stage steps...")

    def NEXUS_SERVICE_LOGIN_USERNAME = null
    def NEXUS_SERVICE_LOGIN_PASSWORD = null
    withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        NEXUS_SERVICE_LOGIN_USERNAME = "${USERNAME}"
        NEXUS_SERVICE_LOGIN_PASSWORD = "${PASSWORD}"
    }

    sh("mvn -Djacoco.skip=true -DskipSourceCompile=true -DskipTestCompile=true -Dskip.surefire.tests=true -Dskip.failsafe.tests=true -Dartifact.repo.host=${NEXUS_SERVICE_HOST} -Dartifact.repo.port=${NEXUS_SERVICE_PORT} -Ddockerfile.username=${NEXUS_SERVICE_LOGIN_USERNAME} -Ddockerfile.password=${NEXUS_SERVICE_LOGIN_PASSWORD} -s dynamic-settings.xml -e deploy")

    echo("Completed [Publish to Artifact Repository] stage steps.")
}
