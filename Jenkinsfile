node() {

    def repoURL = 'https://github.com/ravidkone/TV_Weather_Test.git'

    stage("Prepare Workspace") {
        cleanWs()
        env.WORKSPACE_LOCAL = sh(returnStdout: true, script: 'pwd').trim()
        env.BUILD_TIME = sh(returnStdout: true, script: 'date +%F-%T').trim()
        echo "Workspace set to:" + env.WORKSPACE_LOCAL
        echo "Build time:" + env.BUILD_TIME
    }
    stage('Checkout Self') {
        git branch: 'master', credentialsId: '', url: repoURL
    }
    stage('Cucumber Tests') {
        withMaven(maven: 'maven35') {
            sh """
			cd ${env.WORKSPACE_LOCAL}
			mvn clean test
		"""
        }
    }
    stage('Expose report') {
        archive "**/testng-results.xml"
        cucumber '**/testng-results.xml'
    }
	stage('Import results to Xray') {

		def description = "[BUILD_URL|${env.BUILD_URL}]"
		def labels = '["regression","auto_regression"]'
		def environment = "DEV"
		def testExecutionFieldId = 10008
		def testEnvironmentFieldName = "customfield_10032"
		def projectKey = "TestCRM"
		def xrayConnectorId = 'db347c94-ae58-4196-b429-09923c7cd249'
		def info = '''{
				"fields": {
					"project": {
					"key": "''' + projectKey + '''"
				},
				"labels":''' + labels + ''',
				"description":"''' + description + '''",
				"summary": "Automated Regression Execution @ ''' + env.BUILD_TIME + ' ' + environment + ''' " ,
				"issuetype": {
				"id": "''' + testExecutionFieldId + '''"
				},
				"''' + testEnvironmentFieldName + '''" : [
				"''' + environment + '''"
				]
				}
				}'''

			echo info
			step([$class: 'XrayImportBuilder', endpointName: '/testng', importFilePath: 'test-output/testng-results.xml', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
		        //  step ([ $class:'XrayImportBuilder',endpointName:'/testng/multipart',importFilePath:'test-output/testng-results.xml',importInfo:info,inputInfoSwitcher:'fileContent',serverInstance:xrayConnectorId ])
				//	step([$class: 'XrayImportBuilder', endpointName: '/testng', importFilePath: 'test-output\\testng-results.xml', importToSameExecution: 'false', projectKey: 'TestCRM', serverInstance: 'SERVER-db347c94-ae58-4196-b429-09923c7cd249', testExecKey: 'CRM-13'])		           
		            }
}
