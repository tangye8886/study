//git 代码仓库地址
def git_url="https://github.com/tangye8886/study.git"

//镜像版本号
def tag="latest"
//harbor 镜像仓库地址
def harbor_url="192.168.196.101:85"
//harbor 仓库项目名称
def harbor_project_name="study"
//harbor 登录凭证
def harbor_login_auth="d7a083ba-c311-40c0-8aaf-2ae8d0ca1732"

node{
     stage('拉取代码')
     {
        git "${git_url}"
     }

      stage('编译安装公共子模块')
      {
        sh "mvn -f study_common clean install"
      }

      stage('编译安装fdfs子模块')
      {
         sh "mvn -f study_fdfs clean install"
      }

      def selectedProjectNames = "${project_name}".split(",")

      stage('编译打包微服务工程')
      {

         for(int i=0;i<selectedProjectNames.length;i++)
         {
               def projectInfo = selectedProjectNames[i];

               def currentProjectName="${projectInfo}".split("@")[0];

               def currentProjectPort="${projectInfo}".split("@")[1];

                sh "mvn -f ${currentProjectName} clean package dockerfile:build"

                //定义镜像名称
                def imageName= "${currentProjectName}:${tag}"

                //对镜像打上标签
                sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"

                //将镜像推送到 harbor 101
                withCredentials([usernamePassword(credentialsId: "${harbor_login_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
                     //登录
                    sh "docker login -u ${username} -p ${password} ${harbor_url}"
                     //上传镜像
                    sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
                    sh "echo 镜像上传成功！"
                }

                //从harbor拉取镜像 到部署服务器102
                sshPublisher(publishers: [sshPublisherDesc(configName: 'master_server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/opt/jenkins_shell/deploy.sh $harbor_url $harbor_project_name $currentProjectName $tag $currentProjectPort", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])

         }
      }
}