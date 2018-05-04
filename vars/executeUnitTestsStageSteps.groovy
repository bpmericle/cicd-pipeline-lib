#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests] stage steps...")

    sh("mvn -e -DskipSourceCompile=true -DskipTestCompile=true -Djacoco.skip=true test")

    echo("Completed [Unit Tests] stage steps.")
}
