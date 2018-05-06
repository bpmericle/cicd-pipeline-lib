#!/usr/bin/env groovy

def apply(namespace, templateFileName, replacementValues) {
    def appConfigFileTemplate = readFile(file: templateFileName)
    def appConfigFileContent = appConfigFileTemplate.replaceAll('@@ARTIFACT_ID@@', replacementValues['artifactId'])
    appConfigFileContent = appConfigFileContent.replaceAll('@@IMAGE_TAG@@', replacementValues['imageTag'])
    appConfigFileContent = appConfigFileContent.replaceAll('@@NAMESPACE@@', namespace)

    def appConfigFileName = "${namespace}-config.yml"
    writeFile(file: appConfigFileName, text: appConfigFileContent)

    sh("kubectl apply -f ${appConfigFileName}")
}
