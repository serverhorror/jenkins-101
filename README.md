# Jenkins 101

A short introduction to jenkins.

## 001 How to start?

1. Create a file named `Jenkinsfile`
1. add the simplest *pipeline* we can think of

## 002 What's up with `stage`s?

* Stages can have different options or separate agents

    In plain speak that means that you can run on different servers or
    in different docker containers.

    A good example would be where you need to execute actions that require
    Python2 for one part and Python3 for another part.

* Stages are a serialization or parallelization mechanism

    In plain speak that means that you can run either one after the other
    or have several stages run side by side.

    ![Parallel Pipeline](assets/imgs/parallel.png)

* Go! Take a look!

## 003 What should be executed

### What should and should not be in a Jenkinsfile

* Create environment native scripts that are as simple as possible to use.

    Preferrably it should be a simple shell step that is *well behaved*. *Well behaved* means that you have an exit code of 0 in the success case and and any other valid exit code to indicate an error.

    Your script should provide meaningful output. If you use *well known build tools* it should not be a problem.

    Good examples of things that you could call from within a Jenkins pipeline:

    * *multiple **simple** steps* would be OK because that will allow Jenkins to make certain optimizations
    * Another choice would be to put these in separate stages

    * Maven
        1. `mvn test`
        1. `mvn build`
    * GNU Autotools
        1. `./configure`
        1. `make`
        1. `make install`
    * Gradle
        1. `gradle test`
        1. `gradle build`
    * &#8230; (you name it) &#8230;

* You might be tempted to put most things in the `Jenkinsfile`.

    It is a bad idea to do so. A best practice is to have a reproducible process that you can run &mdash; on your target OS &mdash; from the shell.

    This means, apart from learning how to use *Jenkins* and a `Jenkinsfile` (IOW: a pipeline) you should be using the native build tools that your environment provides.

    For C based environments that would would *autotools* (`./configure`, `make`, `make install` &mdash; see above)

### General procedure

1. Create a script on the command line
1. Run **local** tests to try
1. (OPTIONAL) If you require a specific tool that is hard to install
    use a specialized agent like so: `agent { docker { image 'maven:3-alpine' } }`
    This will make sure that you have reproducible environments that can be run almost anywhere.
1. put a step like `sh 'mvn test'` in your Jenkins file
1. `git commit` and `git push`

/M
