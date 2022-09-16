pipeline {
    agent any

    environment {

        AWS_ACCESS_KEY_ID = credentials('jenkins-aws-secret-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('jenkins-aws-secret-access-key')

        AWS_S3_BUCKET = "playdatenown"
        ARTIFACT_NAME = "playdatenown3.war"
        AWS_EB_APP_NAME = "playdatenowapp"
        AWS_EB_APP_VERSION = "${BUILD_ID}"
        AWS_EB_ENVIRONMENT = "playdatenow-env"

        
        PROJECT_KEY = "playdatenowprojectt"
        SONAR_IP = "http://ec2-54-89-226-254.compute-1.amazonaws.com:9000"
        SONAR_TOKEN = "sqp_1ac5809fa21c8ec38d5d286f3eb30d1088cd74b2"

    }

    stages {
        stage('Validate') 
        {
            steps {
                sh "mvn validate"
                sh "mvn clean"
            }
        }

         stage('Build') 
         {
            steps {
                sh "mvn compile"
            }
        }

        stage('Test') 
        {
            steps {
                sh "mvn test"
            }
        }
        
        stage('Quality Scan')
        {
            steps {
                sh '''
                mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=$PROJECT_KEY \
                    -Dsonar.host.url=$SONAR_IP \
                    -Dsonar.login=$SONAR_TOKEN
                '''
            }
        }
        
        stage('Package') 
        {
            steps {   
                sh "mvn package"
            }

            post {
                success {
                    archiveArtifacts artifacts: '**/target/**.war', followSymlinks: false                   
                }
            }
        }

        stage('Publish artefacts to S3 Bucket') 
        {
            steps {
                sh "aws configure set region us-east-1"
                sh "aws s3 cp ./target/**.war s3://$AWS_S3_BUCKET/$ARTIFACT_NAME"
            }
        }

        stage('db') {
            steps {
                sh "mvn spring-boot:run" 
            }
        }

        stage('Deploy') 
        {
            steps {
                sh 'aws elasticbeanstalk create-application-version --application-name $AWS_EB_APP_NAME --version-label $AWS_EB_APP_VERSION --source-bundle S3Bucket=$AWS_S3_BUCKET,S3Key=$ARTIFACT_NAME'
                sh 'aws elasticbeanstalk update-environment --application-name $AWS_EB_APP_NAME --environment-name $AWS_EB_ENVIRONMENT --version-label $AWS_EB_APP_VERSION'            
            }
        }       
    }
}