#!/usr/bin/env groovy

def call() {
    echo("Executing [Deploy to Pre-Production] stage steps...")

    def pomInfo = readMavenPom()
    def artifactId = pomInfo.artifactId
    def version = pomInfo.version
    def dockerImageTag = "${artifactId}:${version}"
    def dockerHostAndDockerPort = "${NEXUS_HOSTNAME}:${NEXUS_SERVICE_PORT_DOCKER}"
    def dockerRegistryTag = "${dockerHostAndDockerPort}/${dockerImageTag}"
    def appConfigFileName = 'app-config.yml'
    def namespace = 'pre-production'

    def appConfigText = """
kind: Deployment
apiVersion: apps/v1
metadata:
  name: ${artifactId}
  namespace: ${namespace}
spec:
  replicas: 1
  selector:
    matchLabels:
      app: ${artifactId}
  template:
    metadata:
      labels:
        app: ${artifactId}
    spec:
      containers:
      - name: ${artifactId}
        image: ${dockerRegistryTag}
        ports:
        - containerPort: 8090
---
kind: Service
apiVersion: v1
metadata:
  name: ${artifactId}
  namespace: ${namespace}
spec:
  type: LoadBalancer
  ports:
  - name: http
    port: 8611
    targetPort: 8090
  selector:
    app: ${artifactId}
"""
    echo("YAML:\n${appConfigText}")
    writeFile(file: appConfigFileName, text: appConfigText)

    sh("kubectl apply -f ${appConfigFileName}")

    echo("Completed [Deploy to Pre-Production] stage steps.")
}
