#!/usr/bin/env groovy

def call() {
    echo("Executing [Publish to Artifact Repository] stage steps...")

    sh("mvn -DskipSourceCompile=true -DskipTestCompile=true -Dskip.surefire.tests=true -Dskip.failsafe.tests=true -e deploy")

    echo("Completed [Publish to Artifact Repository] stage steps.")
}
