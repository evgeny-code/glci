node {
    // def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }

    stage('Build maven') {
        sh 'mvn clean package'
    }

    stage('Build image') {
        environment {
            GIT_HASH = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
        }
        /* This builds the actual image; synonymous to
         * docker build on the command line */

        /* app = docker.build("evg299/glci") */

        sh 'docker build . -t evg299/glci'
        /* sh 'docker tag evg299/glci localhost:32000/evg299/glci' */
    }

    /*
    stage('Test image') {
         *//* Ideally, we would run a test framework against our image.
         * For this example, we're using a Volkswagen-type approach ;-) *//*

        app.inside {
            sh 'echo "Tests passed"'
        }
    }
    */

    stage('Push image') {
        /*
        environment {
             GIT_HASH = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
        }
        */

        sh 'docker login -u evg299 -p 123qwe123'
        sh 'docker push evg299/glci'


         /*
         * Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. *//*
         */

         /*
         docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            app.push("latest")
         }
         */
    }


    stage('Update k8s') {
            /* sh "microk8s.kubectl set image deployment/glci-app-deployment glci-app-container=evg299/glci:${env.BUILD_NUMBER}" */
            sh "microk8s.kubectl replace --force -f ops/k8s/glci-app-deployment.yaml"
    }
}