#!/usr/bin/env groovy

def notifyPipelineSuccess() {
    def subject = "Pipeline Notification [Pipeline ${JOB_NAME} Succeeded]"
    def body = "The pipeline [<a href=\"${BUILD_URL}\">${BUILD_TAG}</a>] has succeeded."
    notifyByEmail(subject, body)
}

def notifyPipelineFailure() {
    def subject = "Pipeline Notification [Pipeline ${JOB_NAME} Failed]"
    def body = "The pipeline [<a href=\"${BUILD_URL}\">${BUILD_TAG}</a>] has failed. Consult the <a href=\"${BUILD_URL}/consoleFull\">console output</a> to determine the cause."
    notifyByEmail(subject, body)
}

def notifyPipelineAborted() {
    def subject = "Pipeline Notification [Pipeline ${JOB_NAME} Aborted]"
    def body = "The pipeline [<a href=\"${BUILD_URL}\">${BUILD_TAG}</a>] was aborted. Consult the <a href=\"${BUILD_URL}/consoleFull\">console output</a> to determine the cause."
    notifyByEmail(subject, body)
}

def notifyByEmail(subject, body) {
    try {
        mail(subject: subject,
             from: 'notifications@crosslaketech.com',
             to: 'brianm@crosslaketech.com',
             body: body)
    } catch (Exception ex) {
        echo("Caught while trying to email a notification: ${ex.message}")
    }
}
