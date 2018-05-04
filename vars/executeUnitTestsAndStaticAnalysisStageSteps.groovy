#!/usr/bin/env groovy

def call() {
    echo("Executing [Unit Tests and Static Analysis] stage steps...")

    def branches = [:]

    branches['test'] = {sh("mvn -e -Dmaven.main.skip=true -Dmaven.test.skip=true test")}

    branches['static-analysis'] = {sh("mvn -e sonar:sonar")}

    parallel branches

    echo("Completed [Unit Tests and Static Analysis] stage steps.")
}
