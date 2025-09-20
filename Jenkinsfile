pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git branch: 'master', url: 'https://github.com/zakeerhussain074/weather-app.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('weather-service') {
                    sh 'docker run --rm -v $PWD:/app -w /app maven:3.9.6-eclipse-temurin-17 mvn -f weather-service clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('weather-ui') {
                    sh 'docker run --rm -v $PWD:/app -w /app node:18 npm install'
                    sh 'docker run --rm -v $PWD:/app -w /app node:18 npm run build -- --configuration=production'
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
