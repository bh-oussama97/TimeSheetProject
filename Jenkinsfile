 pipeline {
    agent any
    tools {
        maven "Maven"
        jdk "Java 8 "
    }
    stages {

        stage('clone git repo'){
            steps {
                bat "if exist build rmdir /s /q build"
                bat "git clone https://github.com/bh-oussama97/TimeSheetProject.git ./build"
            }
        }
       
         stage('sonar'){
            steps {
                dir("build"){
                    bat "mvn clean install sonar:sonar"
                }
            }
        }
        
        stage("mvn test") {
            steps {
                dir("build"){
                    bat "mvn test"
                }
            }
        }
     stage("mvn package") {
            steps {
                dir("build"){
                    bat "mvn package -Dmaven.test.skip"
                }
            }
        }
          
         
        stage('Building docker image') {
          steps{
                dir("build") {
                    bat "docker build -f Dockerfile -t app.jar ."
                }
            }    
        }
            
        stage('Run docker image') {
            steps{
                dir("build") {
                    bat "docker run -d -p 8088:8088 app.jar"
                }
            }    
        }
        stage("Deployment stage") {
            steps {
                dir('build') {
                    bat "mvn clean deploy"
                    
                }
            }
        }    
       
    }
}