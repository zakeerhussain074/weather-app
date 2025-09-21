pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'maven'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Cloning repo..."
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[url: 'https://github.com/zakeerhussain074/weather-app.git']]
                ])
            }
        }

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
                    nodejs(nodeJSInstallationName: 'NodeJS-20') {
                        sh "npm install"
                        sh "npm run build -- --configuration=production"
                    }
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
             sh """
             docker-compose down || true
             docker network rm weather-app_default || true
             docker-compose up -d
             """
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
