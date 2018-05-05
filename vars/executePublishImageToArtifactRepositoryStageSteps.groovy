#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish Image] stage steps...")

    def pomInfo = readMavenPom()

    withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'NEXUS_SERVICE_LOGIN_USERNAME', passwordVariable: 'NEXUS_SERVICE_LOGIN_PASSWORD')]) {
        docker.withRegistry("http://${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT}/repository/crosslake-docker/", 'nexus') {
            def customImage = docker.build("${pomInfo.artifactId}:${pomInfo.version}", "--build-arg JAR_FILE=${pomInfo.artifactId}-${pomInfo.version}.jar")

            /* Push the container to the custom Registry */
            customImage.push()
        }
    }

    echo("Completed [Publish Image] stage steps.")
}
