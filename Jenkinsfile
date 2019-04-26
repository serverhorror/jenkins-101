
pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '2'))
        timeout(time: 15, unit: 'MINUTES')
    }

    triggers {
        pollSCM("H/5 * * * *")
    }

    stages {
        stage('build'){
            agent { docker { image 'maven:3' } }
            steps {
                sh 'mvn --batch-mode --show-version clean test compile package'
                archiveArtifacts artifacts: '**/*.jar', fingerprint: true
                junit testResults: '**/surefire-reports/*.xml', allowEmptyResults: true
            }
        }
    }
    post {
        success {
            echo 'This message will only display on success'

            emailext attachLog: true, compressLog: true,
                recipientProviders: [developers(), requestor()],
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
        failure {
            echo 'This message will only display on failure'

            // This would send a message to teams
            // if we configure teams to provide us with a connector
            // office365ConnectorSend message:"""Build Failure: ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"""
            //     webhookUrl: 'https://example.invalid/webhook'

            // This would send an email
            // (if it was configured to be able to send mail)
            emailext attachLog: true, compressLog: true,
                recipientProviders: [culprits(), developers()],
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
                // Additional things to attach
                //attachmentsPattern: 'TestResults\\*.trx',
        }
        always {
            echo 'This message will always display'
            // This is a good thing to do
            archiveArtifacts artifacts: '**/*.jar', fingerprint: true
            junit testResults: '**/surefire-reports/*.xml', allowEmptyResults: true
            deleteDir() /* clean up our workspace */
        }
    }
}
