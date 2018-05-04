#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests] stage steps...")

    sh("mvn -e -DskipCompile=true -DskipTestCompile=true test")

    echo("Completed [Unit Tests] stage steps.")
}
