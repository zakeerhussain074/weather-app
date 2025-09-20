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
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
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
