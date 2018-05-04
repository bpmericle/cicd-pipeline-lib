#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests] stage steps...")

    sh("mvn -DskipSourceCompile=true -DskipTestCompile=true -Dskip.failsafe.tests=true -Dmaven.javadoc.skip=true -s dynamic-settings.xml -e test")

    echo("Completed [Unit Tests] stage steps.")
}