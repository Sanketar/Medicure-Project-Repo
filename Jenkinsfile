node{
    
    def mavenHome
    def mavenCMD
    def docker
    def dockerCMD
    def tagName
    
    stage('Prepare Environment'){
        echo 'initialize all the variables'
        mavenHome = tool name: 'maven' , type: 'maven'
        mavenCMD = "${mavenHome}/bin/mvn"
        docker = tool name: 'docker' , type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        dockerCMD = "${docker}/bin/docker"
        tagName="1.0"
    }
    
    stage('Git Code Checkout'){
        try{
            echo 'checkout the code from git repository'
            git 'https://github.com/Sanketar/Medicure-Project-Repo.git'
        }
        catch(Exception e){
            echo 'Exception occurred in Git Code Checkout Stage'
            currentBuild.result = "FAILURE"
            emailext body: '''Dear All,
            The Jenkins job ${JOB_NAME} has been failed. Request you to please have a look at it immediately by clicking on the below link. 
            ${BUILD_URL}''', subject: 'Job ${JOB_NAME} ${BUILD_NUMBER} is failed', to: 'sanket@gmail.com'
        }
    }
    
    stage('Build the Application'){
        echo "Cleaning... Compiling...Testing... Packaging..."
        sh "${mavenCMD} clean package"        
    }
    
    stage('Publish Test Reports'){
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/var/lib/jenkins/workspace/Medicure-Me-Project/target/surefire-reports', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
    }
    
    stage('Containerize the Application'){
        echo 'Creating Docker image'
        sh "sudo ${dockerCMD} build -t sanketar/medicure-me:${tagName} ."
    }
    
    stage('Pushing it to the DockerHub'){
        echo 'Pushing the docker image to DockerHub'
        withCredentials([usernamePassword(credentialsId: 'docker-pass', usernameVariable: 'sanketar', passwordVariable: 'DockHubPass')]){
        sh "sudo ${dockerCMD} login -u sanketar -p ${DockHubPass}"
        sh "sudo ${dockerCMD} push sanketar/medicure-me:${tagName}"    
       }
    } 
    
    stage('Configure and Deploy to the Test-Server'){
        ansiblePlaybook become: true, credentialsId: 'ansible-key', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: 'ansible-playbook.yml'
    }
}
