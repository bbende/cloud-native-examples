pipeline {
    environment {
        REGISTRY_URL = 'https://docker.registry.private'
        REGISTRY_CREDENTIAL = 'docker-registry-private'
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
                    withCredentials([usernamePassword(
                            credentialsId: '${REGISTRY_CREDENTIAL}',
                            usernameVariable: 'REGISTRY_USERNAME',
                            passwordVariable: 'REGISTRY_PASSWORD',
                        )]) {
                        sh 'mvn package -Dimage.skip=false -Dimage.publish=true -Dimage.publish.registry.url=${REGISTRY_URL} -Dimage.publish.registry.username=${REGISTRY_USERNAME} -Dimage.publish.registry.password=${REGISTRY_PASSWORD}'
                    }
                }
            }
        }
    }
}