gitHubLibrary("deployment-library")

Closure releaseBranch = { "main" }

pipeline {
  agent none

  stages {
    stage("Build & Test") {
      agent any
      steps { sh "./gradlew build publishLibPublicationToTestRepository" }
    }

    stage("Publish Snapshot") {
      agent any
      when { branch releaseBranch() }
      steps {
        // Logic to setup credentials and publish encapsulated in deployment-library
        publishMavenSnapshot()
      }
    }

    stage('Release?') {
      // Make sure no agent configured while gathering input
      options {
        timeout(time: 5, unit: 'MINUTES')
      }
      // https://www.jenkins.io/doc/book/pipeline/syntax/#when
      when {
        beforeInput true
        when { branch releaseBranch() }
        not { changeRequest() }
      }
      // https://www.jenkins.io/doc/book/pipeline/syntax/#input
      input {
        message "Continue with Release?"
      }

      // Release stages
      stages {
        stage("Publish Release") {
          agent any
          steps {
            // Logic to setup credentials and publish encapsulated in deployment-library
            publishMavenRelease()
          }
        }
      }
    }
  }
}
