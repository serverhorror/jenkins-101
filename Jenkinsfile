
// This is the *root* node that holds all things
// One important thing to note is that we are using
// the *Declarative Pipeline*.
// That's a DSL especially created to make writing
// pipelines easy (well as easy as Groovy can be...)
pipeline {

    // You always need an agent
    // This just says we don't care which agent
    // we'll be using
    agent any

    // Look! This is new!
    // Find out what it does and discuss!
    options {
        // buildDiscarder(logRotator(numToKeepStr: '2'))
        timeout(time: 15, unit: 'MINUTES')
    }



    triggers {
        pollSCM("H/5 * * * *")
    }

    stages {
        stage('Test'){
            agent {
                docker {
                    image 'golang:latest'
                    // args '-u 0:0'
                    args '--env GOCACHE=$WORKSPACE/.cache'
                }
            }
            steps {
                echo 'running within a pre-defined image'
                sh 'go env'
                sh '''
                    go get github.com/axw/gocov/...
                    go get github.com/AlekSi/gocov-xml
                '''
                sh 'pwd'
                sh 'gocov test | gocov-xml > coverage.xml'
                sh 'go test -v ./...'
                sh 'CGO_ENABLED=0  go build -o ./return main.go'
            }
        }
        stage("Build"){
            agent {
                dockerfile {
                    filename 'Dockerfile'
                    additionalBuildArgs  '--build-arg version=1.0.2'
                    args '-v $WORKSPACE:/src -w /src'
                }
            }

            // See how this is containing no logic?
            // That's the way!
            // Aha!
            // Aha!
            // I like it!
            steps{
                // All build actions are already done
                // See `Dockerfile` for the build logic that
                // is required.
                sh '/return'
            }
        }
        stage('Deploy'){
            steps {
                echo 'This will only run on upon success of all previous stages'
            }
        }
    }
    post {
        success {
            echo 'This message will only display on success'
            archiveArtifacts artifacts: '**/return', fingerprint: true

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
            junit testResults: './coverage.xml', allowEmptyResults: true
            deleteDir() /* clean up our workspace */
        }
    }
}
