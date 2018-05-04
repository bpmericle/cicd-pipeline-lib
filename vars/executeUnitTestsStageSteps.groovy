#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests] stage steps...")

    sh("mvn -e -DskipSourceCompile=true -DskipTestCompile=true install")

    echo("Completed [Unit Tests] stage steps.")
}
