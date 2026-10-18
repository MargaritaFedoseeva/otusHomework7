pipeline {
    agent { label 'java' }

    options {
        timeout(time: 1, unit: 'HOURS')
    }

    parameters {
        string(name: 'ANDROID_URL', defaultValue: 'http://android-1-appium.local:80/', description: 'Адрес инстанса Android')
    }

    stages {
        stage('Print Info') {
                   steps {
                       // Параметр BRANCH доступен здесь автоматически из шаблона!
                       echo "Собираем ветку: ${params.BRANCH}"
                   }
               }
        stage('WireMock Tests') {
            steps {
                container('maven') {
                    withEnv([
                        "ANDROID_URL=${params.ANDROID_URL}"
                    ]) {
                        sh 'mvn clean test'
                    }
                }
            }
        }
    }
}