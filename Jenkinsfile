pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git branch: 'master', url: 'https://github.com/zakeerhussain074/weather-app.git'
            }
        }

        stage('Build Backend') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'   // Maven + JDK 17
                    args '-v /root/.m2:/root/.m2'           // cache dependencies
                }
            }
            steps {
                dir('weather-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            agent {
                docker {
                    image 'node:18'                        // NodeJS + npm
                }
            }
            steps {
                dir('weather-ui') {
                    sh 'npm install'
                    sh 'npm run build -- --configuration=production'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }

        stage('Run Containers') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
