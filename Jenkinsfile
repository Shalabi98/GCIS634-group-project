pipeline {
    agent any
    tools {
        jdk '19'
        gradle 'Gradle 8.4'
    }
    stages {
        stage('Build'){
            steps {
                echo 'Building Password Generator Project'
                script {
                    sh "mvn clean install"
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Running Password Generator Project Tests'
                sh "mvn test"
            }
            post {
                always {
                    archiveArtifacts artifacts: '*/target/.jar', fingerprint: true
                }
                success {
                    echo 'Build successful!'
                }
                failure {
                    echo 'Build failed!'
                 }
            }
        }
    }
}