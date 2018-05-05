#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish Image] stage steps...")

    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def jarFile = "${artifactId}-${version}.jar"
    def dockerImageTag = "${artifactId}:${version}"
    def dockerRegistry = "http://${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT_NEXUS_DOCKER_REPO_PORT}/repository/crosslake-docker/"
    def dockerRegistryTag = "${dockerRegistry}/${dockerImageTag}"

    sh("sudo docker build -t latest -t ${dockerImageTag} -t ${dockerRegistryTag} --build-arg JAR_FILE=${jarFile} .")

    sh("sudo docker push ${dockerRegistryTag}")

    echo("Completed [Publish Image] stage steps.")
}
