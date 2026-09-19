pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        maven 'Maven 3.9'
    }

    options {
        skipDefaultCheckout()
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Lint') {
            steps {
                sh 'mvn pmd:check -Dpmd.printFailingErrors=true'
            }
        }
        stage('Unit tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                sh 'cp target/*.jar /opt/cicd/calculator/releases/calculator-${BUILD_NUMBER}.jar'
                sh 'ln -sfn /opt/cicd/calculator/releases/calculator-${BUILD_NUMBER}.jar /opt/cicd/calculator/current.jar'
                sh 'sudo systemctl restart calculator'
            }
        }
        stage('Smoke test') {
            when {
                branch 'master'
            }
            steps {
                sh 'curl -f --retry 10 --retry-delay 3 --retry-connrefused http://localhost:8081/health'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded'
        }
        failure {
            echo 'Pipeline failed — deployment was blocked'
        }
    }
}
