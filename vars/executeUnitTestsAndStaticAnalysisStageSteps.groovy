#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests and Static Analysis] stage steps...")

    sh("mvn -e clean test")

    echo("Completed [Unit Tests and Static Analysis] stage steps.")
}
