pipeline {
    agent any

    environment {
        // Optional: define tool names if you configured them in Jenkins Global Tool Config
        MAVEN_HOME = tool name: 'Maven', type: 'maven'
        NODEJS_HOME = tool name: 'NodeJS-20', type: 'NodeJS'
        PATH = "${MAVEN_HOME}/bin:${NODEJS_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Build Backend') {
            steps {
                dir('weather-service') {
                    echo "Building Spring Boot backend..."
                    sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('weather-ui') {
                    echo "Building Angular frontend..."
                    sh "npm install"
                    sh "npm run build -- --configuration=production"
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                echo "Building Docker images..."
                sh "docker-compose build"
            }
        }

        stage('Run Containers') {
            steps {
                echo "Running Docker containers..."
                sh "docker-compose up -d"
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully! Your app should be running."
        }
        failure {
            echo "Pipeline failed. Check the logs above."
        }
    }
}
