#!/usr/bin/env groovy

def call() {
    echo("Executing [Deploy to Pre-Production] stage steps...")

    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def dockerImageTag = "${artifactId}:${version}"
    def dockerHostAndDockerPort = "${NEXUS_HOSTNAME}:${NEXUS_SERVICE_PORT_DOCKER}"
    def imageTag = "${dockerHostAndDockerPort}/${dockerImageTag}"
    def namespace = 'pre-production'
    def templateFileName = 'kubernetesfile.yml'
    def replacementValues = [:]
    replacementValues['artifactId'] = artifactId
    replacementValues['imageTag'] = imageTag

    kubernetes.apply(namespace, templateFileName, replacementValues)

    echo("Completed [Deploy to Pre-Production] stage steps.")
}
