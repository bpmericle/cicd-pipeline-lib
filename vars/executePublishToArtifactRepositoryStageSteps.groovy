#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish to Artifact Repository] stage steps...")

    def DOCKER_CLOUD_ID = null
    def DOCKER_CLOUD_PASSWORD = null
    withCredentials([usernamePassword(credentialsId: 'docker-cloud', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        DOCKER_CLOUD_ID = "${USERNAME}"
        DOCKER_CLOUD_PASSWORD = "${PASSWORD}"
    }

    sh("mvn -Djacoco.skip=true -DskipSourceCompile=true -DskipTestCompile=true -Dskip.surefire.tests=true -Dskip.failsafe.tests=true -Dartifact.repo.host=${NEXUS_SERVICE_HOST} -Dartifact.repo.port=${NEXUS_SERVICE_PORT} -Ddockerfile.skip=true -s dynamic-settings.xml -e deploy")

    sh("mvn -Ddockerfile.username=${DOCKER_CLOUD_ID} -Ddockerfile.password=${DOCKER_CLOUD_PASSWORD} dockerfile:build dockerfile:push")

    echo("Completed [Publish to Artifact Repository] stage steps.")
}
