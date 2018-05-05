#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish Image] stage steps...")

    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def jarFile = "${artifactId}-${version}.jar"
    def dockerRegistry = "http://${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT}/repository/crosslake-docker/"
    def dockerImageTag = "${artifactId}:${version}"

    sh("sudo docker build -t ${dockerImageTag} --build-arg JAR_FILE=${jarFile} .")

    echo("Completed [Publish Image] stage steps.")
}
