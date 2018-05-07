#!/usr/bin/env groovy

def call() {
    echo("Executing [Deploy to Pre-Production] stage steps...")

    def namespace = 'pre-production'
    kubernetes.apply(namespace)

    echo("Completed [Deploy to Pre-Production] stage steps.")
}
