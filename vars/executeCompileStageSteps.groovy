#!/usr/bin/env groovy

def call() {
    echo("Executing [Compile] stage steps...")

    sh("mvn -e -Djacoco.skip=true clean test-compile")

    echo("Completed [Compile] stage steps.")
}
