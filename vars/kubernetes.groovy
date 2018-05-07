#!/usr/bin/env groovy

def apply(namespace, templateFileName='kubernetes-app-config-template.yml') {
    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def dockerImageTag = "${artifactId}:${version}"
    def dockerHostAndDockerPort = "${NEXUS_HOSTNAME}:${NEXUS_SERVICE_PORT_DOCKER}"
    def imageTag = "${dockerHostAndDockerPort}/${dockerImageTag}"
    def appConfigFileName = "${namespace}-config.yml"

    def appConfigFileTemplate = readFile(file: templateFileName)
    def appConfigFileContent = appConfigFileTemplate.replaceAll('@@ARTIFACT_ID@@', artifactId).replaceAll('@@IMAGE_TAG@@', imageTag).replaceAll('@@NAMESPACE@@', namespace)

    writeFile(file: appConfigFileName, text: appConfigFileContent)

    sh("kubectl apply -f ${appConfigFileName}")
}
