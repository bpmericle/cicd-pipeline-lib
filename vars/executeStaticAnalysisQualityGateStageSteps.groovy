#!/usr/bin/env groovy

def call() {
    echo("Executing [Static Analysis Quality Gate] stage steps...")

    timeout(time: 45, unit: 'SECONDS') {
        waitForQualityGate(abortPipeline: true)
    }

    echo("Completed [Static Analysis Quality Gate] stage steps.")
}
