#!/usr/bin/env groovy

def call() {
    echo("Executing [Deploy to Pre-Production] stage steps...")

    sh('kubectl get pods')

    echo("Completed [Deploy to Pre-Production] stage steps.")
}
