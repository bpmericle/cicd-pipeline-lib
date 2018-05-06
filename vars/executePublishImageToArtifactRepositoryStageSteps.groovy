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

    sh("sudo docker build -t ${dockerImageTagLatest} -t ${dockerImageTag} -t ${dockerRegistryTag} --build-arg JAR_FILE=${jarFile} .")

    withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'NEXUS_SERVICE_LOGIN_USERNAME', passwordVariable: 'NEXUS_SERVICE_LOGIN_PASSWORD')]) {
        sh("echo ${NEXUS_SERVICE_LOGIN_PASSWORD} | sudo docker login --username ${NEXUS_SERVICE_LOGIN_USERNAME} --password-stdin ${dockerHostAndDockerPort}")
    }

    sh("sudo docker push ${dockerRegistryTag}")

    echo("Completed [Publish Image] stage steps.")
}
