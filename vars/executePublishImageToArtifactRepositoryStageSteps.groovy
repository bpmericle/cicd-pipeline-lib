#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish Image] stage steps...")

    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def jarFile = "${artifactId}-${version}.jar"
    def dockerImageTag = "${artifactId}:${version}"
    def dockerImageTagLatest = "${artifactId}:latest"
    //def dockerHostAndPort = "${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT_NEXUS_HTTPS_PORT}"
    def dockerHostAndDockerPort = "${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT_NEXUS_HTTPS_DOCKER_PORT}"
    def dockerRegistryTag = "${dockerHostAndDockerPort}/${dockerImageTag}"

    sh("sudo docker build -t ${dockerImageTagLatest} -t ${dockerImageTag} --build-arg JAR_FILE=${jarFile} .")

    withCredentials([usernamePassword(credentialsId: 'docker.io', usernameVariable: 'LOGIN_USERNAME', passwordVariable: 'LOGIN_PASSWORD')]) {
        sh("echo ${LOGIN_PASSWORD} | sudo docker login --username ${LOGIN_USERNAME} --password-stdin")
    }

    sh("sudo docker push ${dockerImageTag}")

    echo("Completed [Publish Image] stage steps.")
}
