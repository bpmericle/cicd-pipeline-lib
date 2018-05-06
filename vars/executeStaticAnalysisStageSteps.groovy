#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis] stage steps...")

    node('master') {
        //unstash(name: 'dynamic-settings')

        withSonarQubeEnv('sonarqube') {
            sh("mvn -s dynamic-settings.xml -e sonar:sonar")
        }
    }

    timeout(time: 2, unit: 'MINUTES') {
        waitForQualityGate(abortPipeline: true)
    }

    echo("Completed [Static Analysis] stage steps.")
}
