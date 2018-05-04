#!/usr/bin/env groovy

def call() {
    echo("Executing [Compile] stage steps...")

    sh("mvn -Djacoco.skip=true -e clean test-compile")

    echo("Completed [Compile] stage steps.")
}
