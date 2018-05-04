#!/usr/bin/env groovy

def call() {
    junit(allowEmptyResults: false, testResults: '**/target/failsafe-reports/**/*.xml')
    echo("Completed [Integration Tests] stage with result [success].")
}
