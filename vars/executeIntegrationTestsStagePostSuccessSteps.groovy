#!/usr/bin/env groovy

def call() {
    junit(allowEmptyResults: false, testResults: '**/target/surefire-reports/**/*.xml')
    echo("Completed [Integration Tests] stage with result [success].")
}
