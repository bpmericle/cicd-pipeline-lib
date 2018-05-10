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

def notifyByEmail(subject, body) {
    mail(subject: subject,
         from: 'notifications@crosslaketech.com',
         to: 'brianm@crosslaketech.com',
         body: body)
}
