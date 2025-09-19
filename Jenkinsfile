pipeline {
    agent any

    environment {
        DOCKER_IMAGE_BACKEND = "weather-app-backend"
        DOCKER_IMAGE_FRONTEND = "weather-app-frontend"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/weather-app.git'
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
