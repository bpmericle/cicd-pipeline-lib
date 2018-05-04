#!/usr/bin/env groovy

def call() {
    junit(allowEmptyResults: false, testResults: '**/target/surefire-reports/**/*.xml')
    archiveArtifacts(artifacts: '*-output.txt')
    echo("Completed [Unit Tests and Static Analysis] stage with result [success].")
}
