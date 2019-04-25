
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
    }
}
