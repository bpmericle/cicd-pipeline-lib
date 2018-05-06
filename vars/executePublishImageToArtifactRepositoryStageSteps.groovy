#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish Image] stage steps...")

    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def jarFile = "${artifactId}-${version}.jar"
    def dockerImageTag = "${artifactId}:${version}"
    def dockerImageTagLatest = "${artifactId}:latest"
    def dockerHostAndDockerPort = "${INTERNAL_PROXY_SERVICE_HOST}:${NEXUS_SERVICE_PORT_DOCKER}"
    def dockerRegistryTag = "${dockerHostAndDockerPort}/${dockerImageTag}"

    sh("sudo docker build -t ${dockerImageTag} -t ${dockerRegistryTag} -t ${dockerImageTagLatest} --build-arg JAR_FILE=${jarFile} .")

    withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'LOGIN_USERNAME', passwordVariable: 'LOGIN_PASSWORD')]) {
        sh("echo ${LOGIN_PASSWORD} | sudo docker login --username ${LOGIN_USERNAME} --password-stdin")
    }

    sh("sudo docker push ${dockerRegistryTag}")

    echo("Completed [Publish Image] stage steps.")
}
