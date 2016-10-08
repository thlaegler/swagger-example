package com.itemis.swagger.generator

import io.swagger.codegen.SupportingFile
import io.swagger.codegen.languages.JavaResteasyServerCodegen
import java.io.File
import io.swagger.codegen.CodegenOperation
import io.swagger.models.Operation
import io.swagger.models.Swagger
import io.swagger.models.Model
import java.util.Map

/**
 * 
 */
class ItemisServerCodegen extends JavaResteasyServerCodegen {

	new() {
		super()

		artifactId = 'swagger-example-api'

		outputFolder = 'generated-code/itemis-swagger'
		apiTemplateFiles.put('apiService.mustache', '.java')
		apiTemplateFiles.put('apiServiceImpl.mustache', '.java')
		apiTemplateFiles.put('apiServiceFactory.mustache', '.java')

		// Test Files
		apiTestTemplateFiles.clear
		apiTestTemplateFiles.put('apiIntegrationTest.mustache', '.java')
		apiTestTemplateFiles.put('apiCucumberFeatureSteps.mustache', '.java')
		apiTestTemplateFiles.put('apiCucumberFeature.mustache', '.feature')

		// clear model and api doc template as AbstractJavaJAXRSServerCodegen
		// does not support auto-generated markdown doc at the moment
		// TODO: add doc templates
		modelDocTemplateFiles.remove('model_doc.mustache')
		apiDocTemplateFiles.remove('api_doc.mustache')

		dateLibrary = 'legacy'; // TODO: change to joda
		embeddedTemplateDir = templateDir = 'JavaJaxRS' + File.separator + 'resteasy';
// TODO: Only a few templates are customized.
//		embeddedTemplateDir = templateDir = '''itemis«File.separator»swagger'''
	}

	override String getName() '''itemis-swagger'''

	override String getHelp() '''Generates an Example Java JAXRS-Resteasy Server application.'''

	override void processOpts() {
		super.processOpts()

		writeOptional(outputFolder, new SupportingFile('pom.mustache', '', 'pom.xml'))
		writeOptional(outputFolder, new SupportingFile('gradle.mustache', '', 'build.gradle'))
		writeOptional(outputFolder, new SupportingFile('settingsGradle.mustache', '', 'settings.gradle'))
		writeOptional(outputFolder, new SupportingFile('README.mustache', '', 'README.md'));
		writeOptional(outputFolder, new SupportingFile('web.mustache', ('src/main/webapp/WEB-INF'), 'web.xml'));
		writeOptional(outputFolder,
			new SupportingFile('jboss-web.mustache', ('src/main/webapp/WEB-INF'), 'jboss-web.xml'))
		writeOptional(outputFolder,
			new SupportingFile('RestApplication.mustache', (sourceFolder + '/' + invokerPackage).replace('.', '/'),
				'RestApplication.java'))

		supportingFiles.addAll(
			new SupportingFile('ApiException.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'ApiException.java'),
			new SupportingFile('ApiOriginFilter.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'ApiOriginFilter.java'),
			new SupportingFile('ApiResponseMessage.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'ApiResponseMessage.java'),
			new SupportingFile('NotFoundException.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'NotFoundException.java'),
			new SupportingFile('StringUtil.mustache', (sourceFolder + '/' + invokerPackage).replace('.', '/'),
				'StringUtil.java'),
			new SupportingFile('CucumberTest.mustache', (testFolder + '/' + apiPackage).replace('.', '/'),
				'CucumberTest.java'),
			new SupportingFile('AbstractIntegrationTest.mustache', (testFolder + '/' + apiPackage).replace('.', '/'),
				'AbstractIntegrationTest.java'),
			new SupportingFile('AbstractCucumberFeatureSteps.mustache',
				(testFolder + '/' + apiPackage).replace('.', '/'), 'AbstractFeatureSteps.java'))

		if ('joda'.equals(dateLibrary)) {
			supportingFiles.addAll(
				new SupportingFile('JacksonConfig.mustache', (sourceFolder + '/' + invokerPackage).replace('.', '/'),
					'JacksonConfig.java'),
				new SupportingFile('JodaDateTimeProvider.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
					'JodaDateTimeProvider.java'),
				new SupportingFile('JodaLocalDateProvider.mustache',
					(sourceFolder + '/' + apiPackage).replace('.', '/'), 'JodaLocalDateProvider.java'))
		} else if (dateLibrary.startsWith('java8')) {
			supportingFiles.addAll(
				new SupportingFile('OffsetDateTimeProvider.mustache',
					(sourceFolder + '/' + apiPackage).replace('.', '/'), 'OffsetDateTimeProvider.java'),
				new SupportingFile('LocalDateProvider.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
					'LocalDateProvider.java')
			)
		}
	}

	override String apiTestFilename(String templateName, String tag) {
		var String result = super.apiTestFilename(templateName, tag)

		if (templateName.endsWith('IntegrationTest.mustache')) {
			val int ix = result.lastIndexOf('/')
			result = result.replace('Test', '')
			result = result.substring(0, ix) + '/integration/' + initialCaps(tag) + 'ApiIntegrationTest.java'
			result = result.replace(apiFileFolder(), testFolder + '/' + apiPackage().replace('.', '/'))
		} else if (templateName.endsWith('CucumberFeature.mustache')) {
			val int ix = result.lastIndexOf('/')
			result = result.substring(0, ix) + '/feature/'+tag + '/' + tag + '.feature'
			result = result.replace(apiFileFolder(), testFolder + '/feature')
		} else if (templateName.endsWith('CucumberFeatureSteps.mustache')) {
			val int ix = result.lastIndexOf('/')
			result = result.replace('Test', '')
			result = result.substring(0, ix) + '/feature/'+tag + '/' + initialCaps(tag) + 'ApiFeatureSteps.java'
			result = result.replace(apiFileFolder(), testFolder + '/' + apiPackage().replace('.', '/'))
		}
		result
	}

	override ItemisCodegenOperation fromOperation(String path, String httpMethod, Operation operation,
		Map<String, Model> definitions, Swagger swagger) {
		val CodegenOperation op = super.fromOperation(path, httpMethod, operation, definitions, swagger)
		val ItemisCodegenOperation iop = new ItemisCodegenOperation => [
			httpMethodLowerCase = httpMethod.toLowerCase
			description = operation.description
			allParams = op.allParams
			authMethods = op.authMethods
			baseName = op.baseName
			bodyParam = op.bodyParam
			bodyParams = op.bodyParams
			consumes = op.consumes
			defaultResponse = op.defaultResponse
			discriminator = op.discriminator
			examples = op.examples
			externalDocs = op.externalDocs
			formParams = op.formParams
			hasAuthMethods = op.hasAuthMethods
			hasConsumes = op.hasConsumes
			hasMore = op.hasMore
			hasOptionalParams = op.hasOptionalParams
			hasParams = op.hasParams
			hasProduces = op.hasProduces
			hasReference = op.hasReference
			headerParams = op.headerParams
			it.httpMethod = op.httpMethod
			imports = op.imports
			isListContainer = op.isListContainer
			isMapContainer = op.isMapContainer
			isMultipart = op.isMultipart
			isResponseBinary = op.isResponseBinary
			nickname = op.nickname
			notes = op.notes
			operationId = op.operationId
			operationIdLowerCase = op.operationIdLowerCase
			it.path = op.path
			pathParams = op.pathParams
			produces = op.produces
			queryParams = op.queryParams
			responses = op.responses
			returnBaseType = op.returnBaseType
			returnContainer = op.returnContainer
			returnSimpleType = op.returnSimpleType
			returnType = op.returnType
			returnTypeIsPrimitive = op.returnTypeIsPrimitive
			subresourceOperation = op.subresourceOperation
			summary = op.summary
			tags = op.tags
			unescapedNotes = op.unescapedNotes
			vendorExtensions = op.vendorExtensions
		]

		iop.path = sanitizePath(iop.path)

		iop
	}

	private def String sanitizePath(String p) {
		// prefer replace a ", instead of a fuLL URL encode for readability
		return p.replaceAll("\"", "%22");
	}
}
