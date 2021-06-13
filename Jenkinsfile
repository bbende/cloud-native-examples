pipeline {
    environment {
        REGISTRY_URL = 'https://docker.registry.private'
        REGISTRY_CREDENTIAL = credentials('docker-registry-private')
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
                    sh "mvn package -Dimage.skip=false -Dimage.publish=true -Dimage.publish.registry.url=${REGISTRY_URL} -Dimage.publish.registry.username=${REGISTRY_CREDENTIAL_USR} -Dimage.publish.registry.password=${REGISTRY_CREDENTIAL_PSW}"
                }
            }
        }
    }
}