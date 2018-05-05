#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis] stage steps...")

    withSonarQubeEnv('sonarqube') {
        sh("mvn -s dynamic-settings.xml -e sonar:sonar")
    }

    echo("Completed [Static Analysis] stage steps.")
}
