#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish Image] stage steps...")

    def pomInfo = readMavenPom()
    def dockerRegistry = "http://${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT}/repository/crosslake-docker/"
    def dockerImageName = "${pomInfo.artifactId}:${pomInfo.version}"
    def appJarFile"${pomInfo.artifactId}-${pomInfo.version}.jar"

    sh("sudo docker build -t ${dockerImageName} --build-arg JAR_FILE=${appJarFile} .")

    echo("Completed [Publish Image] stage steps.")
}
