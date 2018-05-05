#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish to Artifact Repository] stage steps...")

    sh("mvn -Djacoco.skip=true -DskipSourceCompile=true -DskipTestCompile=true -Dskip.surefire.tests=true -Dskip.failsafe.tests=true -Dartifact.repo.host=${NEXUS_SERVICE_HOST} -Dartifact.repo.port=${NEXUS_SERVICE_PORT} -s dynamic-settings.xml -e deploy")
/*
    withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'NEXUS_SERVICE_LOGIN_USERNAME', passwordVariable: 'NEXUS_SERVICE_LOGIN_PASSWORD')]) {
    }
*/      
    echo("Completed [Publish to Artifact Repository] stage steps.")
}
