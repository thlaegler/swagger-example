package com.laegler.rest.generator

import io.swagger.codegen.CodegenModel
import io.swagger.codegen.CodegenOperation
import io.swagger.codegen.CodegenProperty
import io.swagger.codegen.CodegenResponse
import io.swagger.codegen.SupportingFile
import io.swagger.codegen.languages.JavaResteasyServerCodegen
import io.swagger.models.Operation
import java.io.File
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.apache.commons.lang3.BooleanUtils

/**
 * 
 */
class JavaResteasyMockServerCodegen extends JavaResteasyServerCodegen {

	new() {
		super()

		artifactId = 'swagger-jaxrs-resteasy-mock-server'

		outputFolder = 'generated-code/javaJaxRSMock'
		apiTemplateFiles.put('apiService.mustache', '.java')
		apiTemplateFiles.put('apiServiceImpl.mustache', '.java')
		apiTemplateFiles.put('apiServiceFactory.mustache', '.java')
		apiTestTemplateFiles.clear() // TODO: add test template
		// clear model and api doc template as AbstractJavaJAXRSServerCodegen
		// does not support auto-generated markdown doc at the moment
		// TODO: add doc templates
		modelDocTemplateFiles.remove('model_doc.mustache')
		apiDocTemplateFiles.remove('api_doc.mustache')

		dateLibrary = 'legacy' // TODO: change to joda
		embeddedTemplateDir = templateDir = '''JavaJaxRS«File.separator»resteasy'''
		//embeddedTemplateDir = templateDir = '''JavaJaxRS«File.separator»resteasyMock'''
	}

	override def String getName() '''jaxrs-resteasy'''
	//override def String getName() '''jaxrs-resteasy-mock'''

	override def String getHelp() '''Generates a Java JAXRS-Resteasy Mock Server application.'''

	override def void processOpts() {
		super.processOpts

		writeOptional(outputFolder, new SupportingFile('pom.mustache', '', 'pom.xml'))
		writeOptional(outputFolder, new SupportingFile('gradle.mustache', '', 'build.gradle'))
		writeOptional(outputFolder, new SupportingFile('settingsGradle.mustache', '', 'settings.gradle'))
		writeOptional(outputFolder, new SupportingFile('README.mustache', '', 'README.md'))
		supportingFiles.add(new SupportingFile("modelBuilder.mustache",
                (sourceFolder + '/' + modelPackage).replace(".", "/"), "Builder.java"));
		supportingFiles.add(
			new SupportingFile('ApiException.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'ApiException.java'))
		supportingFiles.add(
			new SupportingFile('ApiOriginFilter.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'ApiOriginFilter.java'))
		supportingFiles.add(
			new SupportingFile('ApiResponseMessage.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'ApiResponseMessage.java'))
		supportingFiles.add(
			new SupportingFile('NotFoundException.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
				'NotFoundException.java'))
		writeOptional(outputFolder, new SupportingFile('web.mustache', ('src/main/webapp/WEB-INF'), 'web.xml'))
		writeOptional(outputFolder,
			new SupportingFile('jboss-web.mustache', ('src/main/webapp/WEB-INF'), 'jboss-web.xml'))
		writeOptional(outputFolder,
			new SupportingFile('RestApplication.mustache', (sourceFolder + '/' + invokerPackage).replace('.', '/'),
				'RestApplication.java'))
		supportingFiles.add(
			new SupportingFile('StringUtil.mustache', (sourceFolder + '/' + invokerPackage).replace('.', '/'),
				'StringUtil.java'))

		if ('joda'.equals(dateLibrary)) {
			supportingFiles.add(
				new SupportingFile('JacksonConfig.mustache', (sourceFolder + '/' + invokerPackage).replace('.', '/'),
					'JacksonConfig.java'))
			supportingFiles.add(
				new SupportingFile('JodaDateTimeProvider.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
					'JodaDateTimeProvider.java'))
			supportingFiles.add(
				new SupportingFile('JodaLocalDateProvider.mustache',
					(sourceFolder + '/' + apiPackage).replace('.', '/'), 'JodaLocalDateProvider.java'))
		} else if (dateLibrary.startsWith('java8')) {
			supportingFiles.add(
				new SupportingFile('OffsetDateTimeProvider.mustache',
					(sourceFolder + '/' + apiPackage).replace('.', '/'), 'OffsetDateTimeProvider.java'))
			supportingFiles.add(
				new SupportingFile('LocalDateProvider.mustache', (sourceFolder + '/' + apiPackage).replace('.', '/'),
					'LocalDateProvider.java'))
		}
	}

	override def void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co,
		Map<String, List<CodegenOperation>> operations) {
		var String basePath = resourcePath
		if (basePath.startsWith('/')) {
			basePath = basePath.substring(1)
		}
		var int pos = basePath.indexOf('/')
		if (pos > 0) {
			basePath = basePath.substring(0, pos)
		}

		if (basePath == '') {
			basePath = 'default'
		} else {
			if (co.path.startsWith('/' + basePath)) {
				co.path = co.path.substring(('/' + basePath).length)
			}
			co.subresourceOperation = !co.path.isEmpty()
		}
		var List<CodegenOperation> opList = operations.get(basePath)
		if (opList == null) {
			opList = new ArrayList<CodegenOperation>
			operations.put(basePath, opList)
		}
		opList.add(co)
		co.baseName = basePath
	}

	override def Map<String, Object> postProcessOperations(Map<String, Object> objs) {
		val Map<String, Object> operations = objs.get('operations') as Map<String, Object>
		if (operations != null) {
			val List<CodegenOperation> ops = operations.get('operation') as List<CodegenOperation>
			for (CodegenOperation operation : ops) {
				if (operation.hasConsumes == Boolean.TRUE) {
					val Map<String, String> firstType = operation.consumes.get(0)
					if (firstType != null) {
						if ('multipart/form-data'.equals(firstType.get('mediaType'))) {
							operation.isMultipart = Boolean.TRUE
						}
					}
				}
				val List<CodegenResponse> responses = operation.responses
				if (responses != null) {
					for (CodegenResponse resp : responses) {
						if ('0'.equals(resp.code)) {
							resp.code = '200'
						}
					}
				}
				if (operation.returnType == null) {
					operation.returnType = 'Void'
				} else if (operation.returnType.startsWith('List')) {
					val String rt = operation.returnType
					val int end = rt.lastIndexOf('>')
					if (end > 0) {
						operation.returnType = rt.substring('List<'.length(), end).trim()
						operation.returnContainer = 'List'
					}
				} else if (operation.returnType.startsWith('Map')) {
					val String rt = operation.returnType
					val int end = rt.lastIndexOf('>')
					if (end > 0) {
						operation.returnType = rt.substring('Map<'.length(), end).split(',').get(1).trim
						operation.returnContainer = 'Map'
					}
				} else if (operation.returnType.startsWith('Set')) {
					val String rt = operation.returnType
					val int end = rt.lastIndexOf('>')
					if (end > 0) {
						operation.returnType = rt.substring('Set<'.length(), end).trim()
						operation.returnContainer = 'Set'
					}
				}
			}
		}
		objs
	}

	override def void postProcessModelProperty(CodegenModel model, CodegenProperty property) {
		// Add imports for Jackson
		if (!BooleanUtils.toBoolean(model.isEnum)) {
			model.imports.add('JsonProperty')

			if (BooleanUtils.toBoolean(model.hasEnums)) {
				model.imports.add('JsonValue')
			}
		}
	}

	override def Map<String, Object> postProcessModelsEnum(Map<String, Object> _objs) {
		val objs = super.postProcessModelsEnum(_objs)

		// Add imports for Jackson
		val List<Map<String, String>> imports = objs.get('imports') as List<Map<String, String>>
		val List<Object> models = objs.get('models') as List<Object>
		for (Object _mo : models) {
			val Map<String, Object> mo = _mo as Map<String, Object>
			val CodegenModel cm = mo.get('model') as CodegenModel
			// for enum model
			if (Boolean.TRUE.equals(cm.isEnum) && cm.allowableValues != null) {
				cm.imports.add(importMapping.get('JsonValue'))
				val Map<String, String> item = new HashMap<String, String>()
				item.put('import', importMapping.get('JsonValue'))
				imports.add(item)
			}
		}

		objs
	}
}
