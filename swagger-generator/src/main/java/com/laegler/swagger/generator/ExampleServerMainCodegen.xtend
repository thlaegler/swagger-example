package com.laegler.swagger.generator

import io.swagger.codegen.CodegenModel
import io.swagger.codegen.CodegenParameter
import io.swagger.codegen.CodegenProperty
import io.swagger.codegen.CodegenResponse
import io.swagger.codegen.SupportingFile
import io.swagger.codegen.examples.ExampleGenerator
import io.swagger.codegen.languages.JavaResteasyServerCodegen
import io.swagger.models.Model
import io.swagger.models.Operation
import io.swagger.models.Response
import io.swagger.models.Swagger
import io.swagger.models.parameters.BodyParameter
import io.swagger.models.parameters.CookieParameter
import io.swagger.models.parameters.FormParameter
import io.swagger.models.parameters.HeaderParameter
import io.swagger.models.parameters.Parameter
import io.swagger.models.parameters.PathParameter
import io.swagger.models.parameters.QueryParameter
import io.swagger.models.properties.ArrayProperty
import io.swagger.models.properties.Property
import java.io.File
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import java.util.regex.Matcher
import java.util.regex.Pattern
import org.apache.commons.lang3.BooleanUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 
 */
class ExampleServerMainCodegen extends JavaResteasyServerCodegen {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ExampleServerMainCodegen);

	protected Map<String, String> apiIntegrationTestTemplateFiles = new HashMap<String, String>();
	protected Map<String, String> apiFeatureStepsTemplateFiles = new HashMap<String, String>();
	protected String testFolder;

	def Map<String, String> apiIntegrationTestTemplateFiles() {
		return apiIntegrationTestTemplateFiles;
	}

	def Map<String, String> apiFeatureStepsTemplateFiles() {
		return apiFeatureStepsTemplateFiles;
	}

	def String apiIntegrationTestFilename(String templateName, String tag) {
		val String suffix = apiIntegrationTestTemplateFiles().get(templateName);
		return apiIntegrationTestFileFolder() + '/' + toApiIntegrationTestFilename(tag) + suffix;
	}

	def String apiFeatureStepsFilename(String templateName, String tag) {
		val String suffix = apiFeatureStepsTemplateFiles().get(templateName);
		return apiFeatureStepsFileFolder() + '/' + toApiFeatureStepsFilename(tag) + suffix;
	}

	def String apiIntegrationTestFileFolder() {
		return testFolder + '/' + apiPackage.replace('.', '/');
	}

	def String apiFeatureStepsFileFolder() {
		return testFolder + '/' + apiPackage.replace('.', '/');
	}

	def String toApiIntegrationTestFilename(String name) {
		return toApiName(name) + 'IntegrationTest';
	}

	def String toApiFeatureStepsFilename(String name) {
		return toApiName(name) + 'FeatureSteps';
	}

	new() {
		super()
		testFolder = "src/test/java"

		artifactId = 'swagger-jaxrs-resteasy-server'

		outputFolder = 'generated-code/javaJaxRSMock'
		apiTemplateFiles.put('apiService.mustache', '.java')
		apiTemplateFiles.put('apiServiceImpl.mustache', '.java')
		apiTemplateFiles.put('apiServiceFactory.mustache', '.java')
		apiTestTemplateFiles.clear() // TODO: add test template
		apiIntegrationTestTemplateFiles.put('apiIntegrationTest.mustache', '.java')
		apiFeatureStepsTemplateFiles.put('apiFeatureSteps.mustache', '.java')
		// clear model and api doc template as AbstractJavaJAXRSServerCodegen
		// does not support auto-generated markdown doc at the moment
		// TODO: add doc templates
		modelDocTemplateFiles.remove('model_doc.mustache')
		apiDocTemplateFiles.remove('api_doc.mustache')

		dateLibrary = 'legacy' // TODO: change to joda
		embeddedTemplateDir = templateDir = '''JavaJaxRS«File.separator»resteasy'''
	// embeddedTemplateDir = templateDir = '''JavaJaxRS«File.separator»resteasyMock'''
	}

	override def String getName() '''jaxrs-resteasy'''

	// override def String getName() '''jaxrs-resteasy-mock'''
	override def String getHelp() '''Generates a Java JAXRS-Resteasy Mock Server application.'''

	override def void processOpts() {
		super.processOpts

		writeOptional(outputFolder, new SupportingFile('pom.mustache', '', 'pom.xml'))
		writeOptional(outputFolder, new SupportingFile('gradle.mustache', '', 'build.gradle'))
		writeOptional(outputFolder, new SupportingFile('settingsGradle.mustache', '', 'settings.gradle'))
		writeOptional(outputFolder, new SupportingFile('README.mustache', '', 'README.md'))
//		supportingFiles.add(new SupportingFile('modelBuilder.mustache',
//                (sourceFolder + '/' + modelPackage).replace('.', '/'), 'Builder.java'));
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

	def void addOperationToGroup(String tag, String resourcePath, Operation operation, ExampleCodegenOperation co,
		Map<String, List<ExampleCodegenOperation>> operations) {
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
		var List<ExampleCodegenOperation> opList = operations.get(basePath)
		if (opList == null) {
			opList = new ArrayList<ExampleCodegenOperation>
			operations.put(basePath, opList)
		}
		opList.add(co)
		co.baseName = basePath
	}

	override def Map<String, Object> postProcessOperations(Map<String, Object> objs) {
		val Map<String, Object> operations = objs.get('operations') as Map<String, Object>
		if (operations != null) {
			val List<ExampleCodegenOperation> ops = operations.get('operation') as List<ExampleCodegenOperation>
			for (ExampleCodegenOperation operation : ops) {
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

	/**
	 * Convert Swagger Operation object to Codegen Operation object
	 * 
	 * @param path the path of the operation
	 * @param httpMethod HTTP method
	 * @param operation Swagger operation object
	 * @param definitions a map of Swagger models
	 * @param swagger a Swagger object representing the spec
	 * @return Codegen Operation object
	 */
	override ExampleCodegenOperation fromOperation(String path, String httpMethod, Operation operation,
		Map<String, Model> definitions, Swagger swagger) {
//        val ExampleCodegenOperation op = CodegenModelFactory.newInstance(CodegenModelType.OPERATION);
		val ExampleCodegenOperation op = new ExampleCodegenOperation
		val Set<String> imports = new HashSet<String>();
		op.vendorExtensions = operation.getVendorExtensions()

		var String operationId = getOrGenerateOperationId(operation, path, httpMethod);
		operationId = removeNonNameElementToCamelCase(operationId);
		op.path = path;
		op.operationId = toOperationId(operationId)
		op.summary = escapeText(operation.summary)
		op.unescapedNotes = operation.description
		op.notes = escapeText(operation.description)
		op.tags = operation.tags
		op.hasConsumes = false
		op.hasProduces = false
		op.httpMethodLowerCase = httpMethod.toLowerCase

		var List<String> consumes = new ArrayList<String>()
		if (operation.getConsumes() != null) {
			if (operation.getConsumes().size() > 0) {
				// use consumes defined in the operation
				consumes = operation.getConsumes();
			} else {
				// empty list, do nothing to override global setting
			}
		} else if (swagger != null && swagger.getConsumes() != null && swagger.getConsumes().size() > 0) {
			// use consumes defined globally
			consumes = swagger.getConsumes();
			LOGGER.debug(
				'No consumes defined in operation. Using global consumes (' + swagger.getConsumes() + ') for ' +
					op.operationId);
				}

				// if 'consumes' is defined (per operation or using global definition)
				if (consumes != null && consumes.size() > 0) {
					val List<Map<String, String>> c = new ArrayList<Map<String, String>>();
					var int count = 0;
					for (String key : consumes) {
						val Map<String, String> mediaType = new HashMap<String, String>();
						// escape quotation to avoid code injection
						mediaType.put('mediaType', escapeText(escapeQuotationMark(key)));
						count += 1;
						if (count < consumes.size()) {
							mediaType.put('hasMore', 'true');
						} else {
							mediaType.put('hasMore', null);
						}
						c.add(mediaType);
					}
					op.consumes = c;
					op.hasConsumes = true;
				}

				var List<String> produces = new ArrayList<String>();
				if (operation.getProduces() != null) {
					if (operation.getProduces().size() > 0) {
						// use produces defined in the operation
						produces = operation.getProduces();
					} else {
						// empty list, do nothing to override global setting
					}
				} else if (swagger != null && swagger.getProduces() != null && swagger.getProduces().size() > 0) {
					// use produces defined globally
					produces = swagger.getProduces();
					LOGGER.debug(
						'No produces defined in operation. Using global produces (' + swagger.getProduces() + ') for ' +
							op.operationId);
				}

				// if 'produces' is defined (per operation or using global definition)
				if (produces != null && produces.size() > 0) {
					val List<Map<String, String>> c = new ArrayList<Map<String, String>>();
					var int count = 0;
					for (String key : produces) {
						val Map<String, String> mediaType = new HashMap<String, String>();
						// escape quotation to avoid code injection
						mediaType.put('mediaType', escapeText(escapeQuotationMark(key)));
						count += 1;
						if (count < produces.size()) {
							mediaType.put('hasMore', 'true');
						} else {
							mediaType.put('hasMore', null);
						}
						c.add(mediaType);
					}
					op.produces = c;
					op.hasProduces = true;
				}

				if (operation.getResponses() != null && !operation.getResponses().isEmpty()) {
					val Response methodResponse = findMethodResponse(operation.getResponses());

					for (Map.Entry<String, Response> entry : operation.getResponses().entrySet()) {
						val Response response = entry.getValue();
						val CodegenResponse r = fromResponse(entry.getKey(), response);
						r.hasMore = true;
						if (r.baseType != null && !defaultIncludes.contains(r.baseType) &&
							!languageSpecificPrimitives.contains(r.baseType)) {
							imports.add(r.baseType);
						}
						r.isDefault = response == methodResponse;
						op.responses.add(r);
						if (r.isBinary && r.isDefault) {
							op.isResponseBinary = Boolean.TRUE;
						}
					}
					op.responses.get(op.responses.size() - 1).hasMore = false;

					if (methodResponse != null) {
						if (methodResponse.getSchema() != null) {
							val CodegenProperty cm = fromProperty('response', methodResponse.getSchema());

							val Property responseProperty = methodResponse.getSchema();

							if (responseProperty instanceof ArrayProperty) {
								val ArrayProperty ap = responseProperty as ArrayProperty;
								val CodegenProperty innerProperty = fromProperty('response', ap.getItems());
								op.returnBaseType = innerProperty.baseType;
							} else {
								if (cm.complexType != null) {
									op.returnBaseType = cm.complexType;
								} else {
									op.returnBaseType = cm.baseType;
								}
							}
							op.examples = new ExampleGenerator(definitions).generate(
								methodResponse.getExamples(), operation.getProduces(), responseProperty);
							op.defaultResponse = toDefaultValue(responseProperty);
							op.returnType = cm.datatype;
							op.hasReference = definitions != null && definitions.containsKey(op.returnBaseType);

							// lookup discriminator
							if (definitions != null) {
								val Model m = definitions.get(op.returnBaseType);
								if (m != null) {
									val CodegenModel cmod = fromModel(op.returnBaseType, m, definitions);
									op.discriminator = cmod.discriminator;
								}
							}

							if (cm.isContainer != null) {
								op.returnContainer = cm.containerType;
								if ('map'.equals(cm.containerType)) {
									op.isMapContainer = Boolean.TRUE;
								} else if ('list'.equalsIgnoreCase(cm.containerType)) {
									op.isListContainer = Boolean.TRUE;
								} else if ('array'.equalsIgnoreCase(cm.containerType)) {
									op.isListContainer = Boolean.TRUE;
								}
							} else {
								op.returnSimpleType = true;
							}
							if (languageSpecificPrimitives().contains(op.returnBaseType) || op.returnBaseType == null) {
								op.returnTypeIsPrimitive = true;
							}
						}
						addHeaders(methodResponse, op.responseHeaders);
					}
				}

				val List<Parameter> parameters = operation.getParameters();
				var CodegenParameter bodyParam = null;
				val List<CodegenParameter> allParams = new ArrayList<CodegenParameter>();
				val List<CodegenParameter> bodyParams = new ArrayList<CodegenParameter>();
				val List<CodegenParameter> pathParams = new ArrayList<CodegenParameter>();
				val List<CodegenParameter> queryParams = new ArrayList<CodegenParameter>();
				val List<CodegenParameter> headerParams = new ArrayList<CodegenParameter>();
				val List<CodegenParameter> cookieParams = new ArrayList<CodegenParameter>();
				val List<CodegenParameter> formParams = new ArrayList<CodegenParameter>();

				if (parameters != null) {
					for (Parameter param : parameters) {
						val CodegenParameter p = fromParameter(param, imports);
						// rename parameters to make sure all of them have unique names
						if (ensureUniqueParams) {
							p.paramName = enforceUniqueParams(p, allParams);
						}

						// set isPrimitiveType and baseType for allParams
						/*if (languageSpecificPrimitives.contains(p.baseType)) {
						 *     p.isPrimitiveType = true;
						 *     p.baseType = getSwaggerType(p);
						 }*/
						allParams.add(p);
						// Issue #2561 (neilotoole) : Moved setting of is<Type>Param flags
						// from here to fromParameter().
						if (param instanceof QueryParameter) {
							queryParams.add(p.copy());
						} else if (param instanceof PathParameter) {
							pathParams.add(p.copy());
						} else if (param instanceof HeaderParameter) {
							headerParams.add(p.copy());
						} else if (param instanceof CookieParameter) {
							cookieParams.add(p.copy());
						} else if (param instanceof BodyParameter) {
							bodyParam = p;
							bodyParams.add(p.copy());
						} else if (param instanceof FormParameter) {
							formParams.add(p.copy());
						}
						if (p.required == null || !p.required) {
							op.hasOptionalParams = true;
						}
					}
				}
				for (String i : imports) {
					if (needToImport(i)) {
						op.imports.add(i);
					}
				}
				op.bodyParam = bodyParam;
				op.httpMethod = httpMethod.toUpperCase();

				// move 'required' parameters in front of 'optional' parameters
				if (sortParamsByRequiredFlag) {
					Collections.sort(allParams, new Comparator<CodegenParameter>() {
						override int compare(CodegenParameter one, CodegenParameter another) {
							var boolean oneRequired = false
							if (one.required != null) {
								oneRequired = one.required
							}
							var boolean anotherRequired = false
							if (another.required != null) {
								anotherRequired = another.required
							}
							if (oneRequired == anotherRequired) {
								return 0
							} else if (oneRequired) {
								return -1
							} else {
								return 1
							}
						}
					});
				}
				op.allParams = addHasMore(allParams);
				op.bodyParams = addHasMore(bodyParams);
				op.pathParams = addHasMore(pathParams);
				op.queryParams = addHasMore(queryParams);
				op.headerParams = addHasMore(headerParams);
				// op.cookieParams = cookieParams;
				op.formParams = addHasMore(formParams);
				// legacy support
				op.nickname = op.operationId;

				if (op.allParams.size() > 0) {
					op.hasParams = true;
				}
				op.externalDocs = operation.getExternalDocs();

				// set Restful Flag
				op.isRestfulShow = op.isRestfulShow();
				op.isRestfulIndex = op.isRestfulIndex();
				op.isRestfulCreate = op.isRestfulCreate();
				op.isRestfulUpdate = op.isRestfulUpdate();
				op.isRestfulDestroy = op.isRestfulDestroy();
				op.isRestful = op.isRestful();

				op.path = sanitizePath(op.path);

				return op;
			}

			def enforceUniqueParams(CodegenParameter p, List<CodegenParameter> allParams) {
				for (CodegenParameter cp : allParams) {
					if (p.paramName.equals(cp.paramName)) {
						return generateNextName(p.paramName);
					}
				}
				p.paramName
			}

			private def addHeaders(Response response, List<CodegenProperty> target) {
				if (response.getHeaders() != null) {
					for (Map.Entry<String, Property> headers : response.getHeaders().entrySet()) {
						target.add(fromProperty(headers.getKey(), headers.getValue()));
					}
				}
			}

			private static def String generateNextName(String name) {
				val Pattern pattern = Pattern.compile('\\d+\\z');
				val Matcher matcher = pattern.matcher(name);
				if (matcher.find()) {
					val String numStr = matcher.group();
					val int num = Integer.parseInt(numStr) + 1;
					return name.substring(0, name.length() - numStr.length()) + num;
				} else {
					return name + '2';
				}
			}

			private static def List<CodegenParameter> addHasMore(List<CodegenParameter> objs) {
				if (objs != null) {
					for (var int i = 0; i < objs.size(); i++) {
						if (i > 0) {
							objs.get(i).secondaryParam = new Boolean(true);
						}
						if (i < objs.size() - 1) {
							objs.get(i).hasMore = new Boolean(true);
						}
					}
				}
				return objs;
			}

			private def String sanitizePath(String p) {
				// prefer replace a ', instead of a fuLL URL encode for readability
				return p.replaceAll('\'', '%22');
			}
		}
		