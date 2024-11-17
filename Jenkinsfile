pipeline {
    agent any

    tools {
        maven 'Maven'  // Configura Maven en Jenkins
        jdk 'JDK 21'   // Configura JDK 21 en Jenkins
    }

    stages {
        stage('Run Tests') {
            steps {
                script {
                    try {
                        // Ejecuta los tests con el perfil `test`
                        sh 'mvn test -Dspring.profiles.active=test'
                        currentBuild.result = 'SUCCESS'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e // Re-lanza la excepción para que falle el build
                    }
                }
            }
        }
    }

    post {
        success {
            // Enviar correo si los tests pasan
            emailext subject: 'Tests exitosos',
                     body: 'Todos los tests han pasado correctamente.',
                     to: 'brunolucarelli5@gmail.com, brunolucarelli10@gmail.com'
        }

        failure {
            // Enviar correo si los tests fallan
            emailext subject: 'Tests fallidos',
                     body: 'Algunos tests han fallado. Revisa el log en Jenkins.',
                     to: 'brunolucarelli5@gmail.com, brunolucarelli10@gmail.com'
        }
    }
}
