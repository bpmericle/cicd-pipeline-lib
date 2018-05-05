#!/usr/bin/env groovy

def call() {
    echo("Executing [Compile] stage steps...")

    withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        // create dynamic settings.xml failsafe
        def settingsText = """
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <mirrors>
      <mirror>
        <id>crosslake-repo-mirror</id>
        <name>Crosslake Maven Repository Manager</name>
        <url>http://${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT}/repository/crosslake-public/</url>
        <mirrorOf>*</mirrorOf>
      </mirror>
    </mirrors>

    <servers>
      <server>
        <id>nexus</id>
        <username>${NEXUS_SERVICE_LOGIN_USERNAME}</username>
        <password>${NEXUS_SERVICE_LOGIN_PASSWORD}</password>
        <configuration>
          <repository>${NEXUS_SERVICE_HOST}:${NEXUS_SERVICE_PORT}/repository/crosslake-docker/</repository>
          <tag>latest</tag>
          <useMavenSettingsForAuth>true</useMavenSettingsForAuth>
        </configuration>
      </server>
    </servers>
  </settings>
  """
        writeFile(file: 'dynamic-settings.xml', text: settingsText)
    }

    sh("mvn -Djacoco.skip=true -e -s dynamic-settings.xml clean test-compile")

    echo("Completed [Compile] stage steps.")
}
