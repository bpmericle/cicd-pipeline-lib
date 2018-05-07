#!/usr/bin/env groovy

def call() {
    echo("Executing [Deploy to Production] stage steps...")

    def namespace = 'production'
    kubernetes.apply(namespace)

    echo("Completed [Deploy to Production] stage steps.")
}
