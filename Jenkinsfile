pipeline {
    agent any

    tools {
        maven 'Maven'  // El nombre de la instalación de Maven en Jenkins
        jdk 'JDK 21'   // El nombre de la instalación de JDK 21 en Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Obtiene el código fuente desde tu repositorio
                git 'https://github.com/brunolucarelli5/auth-service-testing'
            }
        }

        stage('Build') {
            steps {
                // Construye el proyecto con Maven
                sh 'mvn clean install'
            }
        }

        stage('Run') {
            steps {
                // Ejecuta el proyecto (si es necesario)
                sh 'java -jar target/auth-service-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
