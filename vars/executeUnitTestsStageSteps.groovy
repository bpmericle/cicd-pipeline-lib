#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests] stage steps...")

    sh("mvn -e -DskipCompile=true -DskipTestCompile=true verify")

    echo("Completed [Unit Tests] stage steps.")
}
