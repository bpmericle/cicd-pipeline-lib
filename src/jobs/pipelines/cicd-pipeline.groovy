#!/usr/bin/env groovy

multibranchPipelineJob('pipelines/cicd-app-pipeline') {
    displayName('CICD Application Pipeline')
    description('Declarative Pipeline for the cicd app demo.')

    branchSources {
        git {
            remote('https://github.com/bpmericle/cicd-demo-app.git')
            includes('*')
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(25)
        }
    }
}
