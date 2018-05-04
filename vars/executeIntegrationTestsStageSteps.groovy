#!/usr/bin/env groovy

def call() {
    echo("Executing [Integration Tests] stage steps...")

    sh("mvn -DskipSourceCompile=true -DskipTestCompile=true -Dskip.surefire.tests=true -e integration-test")

    echo("Completed [Integration Tests] stage steps.")
}
