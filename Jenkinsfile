
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

    triggers {
        pollSCM("H/5 * * * *")
    }

    stages {

        // You can have more than one stage
        // in fact:
        // You can have as many as necessary
        // or desired.
        // KNOCK YOURSELF OUT!
        stage('How to get started?') {

            // Each stage has steps
            // every action contained within
            // a steps section is a single
            // step. Feel free to combine as
            // many as you need.
            steps {
                echo 'Look, Ma! No hands!'

                // This is a "script block" it allows you to write
                // imperative code that is executed as one would
                // usually expect.
                // The difference to declarative is that you will
                // have to spell out all the actions that need to be taken.
                script {
                    def names = ['Alice', 'Bob']
                    for (int i = 0; i < names.size(); ++i) {
                        echo "Hello, ${names[i]}!"
                    }
                }
                echo 'This is another step that '
            }
        }
        stage("What's up with stages?"){
            steps{
                echo """(https://jenkins.io/doc/book/pipeline/#stage)

                A stage block defines a conceptually distinct subset of
                tasks performed through the entire Pipeline (e.g. "Build",
                "Test" and "Deploy" stages), which is used by many plugins
                to visualize or present Jenkins Pipeline status/progress. [6]
                """

                echo """Jenkins likes stages because it provides portability
                between different `agents`.

                In plain speak that means that we can run a stage and if
                the agent (server) dies Jenkins will pick up where after
                the last finished stage.
                """

                echo """This can be a blessing or a curse!

                It means that things that aren't `serializable` will not
                work or -- in quite a few cases -- will even be forbidden.
                """
            }
        }

        stage ('Parallel'){
            // Note how we have the `parallel` block around the following section?
            // This means that it will take (roughly) 10 secs in total rather than
            // 20 secs -- which would be the case if we executed in a serialzed
            // fashion
            parallel {
                stage('Python 2'){
                    // See how we define a specific agent here?
                    // This will make sure that all the steps that are following
                    // *within that stage* are executed in the docker image
                    // we requested.
                    agent { docker {image 'python:2'}}
                    steps {
                        sh 'python --version'
                        sh 'sleep 10'
                    }
                }
                stage('Python 3'){
                    // See how we define a specific agent here?
                    // This will make sure that all the steps that are following
                    // *within that stage* are executed in the docker image
                    // we requested.
                    // This one is -- for all intents and purposed -- completely
                    // separate from any other agent in this pipeline.
                    // The only shared thing is the "workspace".
                    // That is: Your source code.
                    agent { docker {image 'python:3'}}
                    steps {
                        sh 'python --version'
                        sh 'sleep 10'
                    }
                }
            }
        }
    }
}
