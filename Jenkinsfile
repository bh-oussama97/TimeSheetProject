pipeline {
    environment
{
registry = "saharesprit1/timesheet"
registryCredential= 'saharesprit1'
dockerImage = ''
}
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
        stage("Deployment stage") {
            steps {
                dir('build') {
                    bat "mvn clean deploy"
                    
                }
            }
        }    
         
        stage('Building our image') { 
            steps { 
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            } 
        }
        stage('Deploy our image') { 
            steps { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
            }
        } 
        stage('Cleaning up') { 
            steps { 
               bat "docker rmi $registry:$BUILD_NUMBER"
            }
        } 
        
        }
        post {
            success {
                emailext body: 'build success' ,subject:'Jenkins' , to : 'sahar.gharbi@esprit.tn'
            }
            failure {
                emailext body: 'build failure' ,subject:'Jenkins' , to : 'sahar.gharbi@esprit.tn'
            }
        }
        }