pipeline {
    environment {
    }
    agent {
        kubernetes {
            defaultContainer 'jnlp'
            yamlFile 'build-pod.yaml'
        }
    }
    stages {
        stage('Build') {
            steps {
                container('maven') {
                    sh 'mvn package'
                }
            }
        }
    }
}