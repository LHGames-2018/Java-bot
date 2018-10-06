node {
    stage('Clone repository') {
        checkout scm
    }

    stage('Initialize'){
        def dockerHome = tool 'docker'
        env.PATH = "${dockerHome}/bin:${env.PATH}"
    }

    stage('Build image') {
        sh 'docker build -t {{image}}:version-$BUILD_NUMBER .'
    }

    stage('Push image') {
        sh 'docker push {{image}}:version-$BUILD_NUMBER'
    }
}
