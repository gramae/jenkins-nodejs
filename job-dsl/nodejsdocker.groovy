job('NodeJS Docker example') {
    scm {
        git('git@github.com:gramae/jenkins-nodejs.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('gramae')
            node / gitConfigEmail('emanuel.grama@oracle.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('gramae/nodejs-jenkins')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
