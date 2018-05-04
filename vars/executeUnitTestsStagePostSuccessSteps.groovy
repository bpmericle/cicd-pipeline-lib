#!/usr/bin/env groovy

def call() {
    junit(allowEmptyResults: false, testResults: '**/target/surefire-reports/**/*.xml')
    echo("Completed [Unit Tests] stage with result [success].")
}
