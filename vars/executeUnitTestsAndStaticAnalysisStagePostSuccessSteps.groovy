#!/usr/bin/env groovy

def call() {
    junit(allowEmptyResults: false, testResults: '**/target/surefire-reports/**/*.xml')
    echo("Completed [Unit Tests and Static Analysis] stage with result [success].")
}
