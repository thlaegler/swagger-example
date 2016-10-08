package com.itemis.swagger.generator;

import com.google.common.base.Objects;
import com.itemis.swagger.generator.ItemisCodegenOperation;
import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenParameter;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.CodegenResponse;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.examples.ExampleGenerator;
import io.swagger.codegen.languages.JavaResteasyServerCodegen;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.CookieParameter;
import io.swagger.models.parameters.FormParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.Property;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.apache.commons.lang3.BooleanUtils;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
@Generated("org.eclipse.xtend.core.compiler.XtendGenerator")
public class ItemisServerMainCodegen extends JavaResteasyServerCodegen {
  protected final static Logger LOGGER = LoggerFactory.getLogger(ItemisServerMainCodegen.class);
  
  protected Map<String, String> apiIntegrationTestTemplateFiles = new HashMap<String, String>();
  
  protected Map<String, String> apiFeatureStepsTemplateFiles = new HashMap<String, String>();
  
  protected String testFolder;
  
  public Map<String, String> apiIntegrationTestTemplateFiles() {
    return this.apiIntegrationTestTemplateFiles;
  }
  
  public Map<String, String> apiFeatureStepsTemplateFiles() {
    return this.apiFeatureStepsTemplateFiles;
  }
  
  public String apiIntegrationTestFilename(final String templateName, final String tag) {
    Map<String, String> _apiIntegrationTestTemplateFiles = this.apiIntegrationTestTemplateFiles();
    final String suffix = _apiIntegrationTestTemplateFiles.get(templateName);
    String _apiIntegrationTestFileFolder = this.apiIntegrationTestFileFolder();
    String _plus = (_apiIntegrationTestFileFolder + "/");
    String _apiIntegrationTestFilename = this.toApiIntegrationTestFilename(tag);
    String _plus_1 = (_plus + _apiIntegrationTestFilename);
    return (_plus_1 + suffix);
  }
  
  public String apiFeatureStepsFilename(final String templateName, final String tag) {
    Map<String, String> _apiFeatureStepsTemplateFiles = this.apiFeatureStepsTemplateFiles();
    final String suffix = _apiFeatureStepsTemplateFiles.get(templateName);
    String _apiFeatureStepsFileFolder = this.apiFeatureStepsFileFolder();
    String _plus = (_apiFeatureStepsFileFolder + "/");
    String _apiFeatureStepsFilename = this.toApiFeatureStepsFilename(tag);
    String _plus_1 = (_plus + _apiFeatureStepsFilename);
    return (_plus_1 + suffix);
  }
  
  public String apiIntegrationTestFileFolder() {
    String _replace = this.apiPackage.replace(".", "/");
    return ((this.testFolder + "/") + _replace);
  }
  
  public String apiFeatureStepsFileFolder() {
    String _replace = this.apiPackage.replace(".", "/");
    return ((this.testFolder + "/") + _replace);
  }
  
  public String toApiIntegrationTestFilename(final String name) {
    String _apiName = this.toApiName(name);
    return (_apiName + "IntegrationTest");
  }
  
  public String toApiFeatureStepsFilename(final String name) {
    String _apiName = this.toApiName(name);
    return (_apiName + "FeatureSteps");
  }
  
  public ItemisServerMainCodegen() {
    super();
    this.testFolder = "src/test/java";
    this.artifactId = "swagger-jaxrs-resteasy-server";
    this.outputFolder = "generated-code/javaJaxRSMock";
    this.apiTemplateFiles.put("apiService.mustache", ".java");
    this.apiTemplateFiles.put("apiServiceImpl.mustache", ".java");
    this.apiTemplateFiles.put("apiServiceFactory.mustache", ".java");
    this.apiTestTemplateFiles.clear();
    this.apiIntegrationTestTemplateFiles.put("apiIntegrationTest.mustache", ".java");
    this.apiFeatureStepsTemplateFiles.put("apiFeatureSteps.mustache", ".java");
    this.modelDocTemplateFiles.remove("model_doc.mustache");
    this.apiDocTemplateFiles.remove("api_doc.mustache");
    this.dateLibrary = "legacy";
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("JavaJaxRS");
    _builder.append(File.separator, "");
    _builder.append("resteasy");
    String _templateDir = (this.templateDir = _builder.toString());
    this.embeddedTemplateDir = _templateDir;
  }
  
  @Override
  public String getName() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("jaxrs-resteasy");
    return _builder.toString();
  }
  
  @Override
  public String getHelp() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Generates a Java JAXRS-Resteasy Mock Server application.");
    return _builder.toString();
  }
  
  @Override
  public void processOpts() {
    super.processOpts();
    SupportingFile _supportingFile = new SupportingFile("pom.mustache", "", "pom.xml");
    this.writeOptional(this.outputFolder, _supportingFile);
    SupportingFile _supportingFile_1 = new SupportingFile("gradle.mustache", "", "build.gradle");
    this.writeOptional(this.outputFolder, _supportingFile_1);
    SupportingFile _supportingFile_2 = new SupportingFile("settingsGradle.mustache", "", "settings.gradle");
    this.writeOptional(this.outputFolder, _supportingFile_2);
    SupportingFile _supportingFile_3 = new SupportingFile("README.mustache", "", "README.md");
    this.writeOptional(this.outputFolder, _supportingFile_3);
    String _replace = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_4 = new SupportingFile("ApiException.mustache", _replace, 
      "ApiException.java");
    this.supportingFiles.add(_supportingFile_4);
    String _replace_1 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_5 = new SupportingFile("ApiOriginFilter.mustache", _replace_1, 
      "ApiOriginFilter.java");
    this.supportingFiles.add(_supportingFile_5);
    String _replace_2 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_6 = new SupportingFile("ApiResponseMessage.mustache", _replace_2, 
      "ApiResponseMessage.java");
    this.supportingFiles.add(_supportingFile_6);
    String _replace_3 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_7 = new SupportingFile("NotFoundException.mustache", _replace_3, 
      "NotFoundException.java");
    this.supportingFiles.add(_supportingFile_7);
    SupportingFile _supportingFile_8 = new SupportingFile("web.mustache", "src/main/webapp/WEB-INF", "web.xml");
    this.writeOptional(this.outputFolder, _supportingFile_8);
    SupportingFile _supportingFile_9 = new SupportingFile("jboss-web.mustache", "src/main/webapp/WEB-INF", "jboss-web.xml");
    this.writeOptional(this.outputFolder, _supportingFile_9);
    String _replace_4 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
    SupportingFile _supportingFile_10 = new SupportingFile("RestApplication.mustache", _replace_4, 
      "RestApplication.java");
    this.writeOptional(this.outputFolder, _supportingFile_10);
    String _replace_5 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
    SupportingFile _supportingFile_11 = new SupportingFile("StringUtil.mustache", _replace_5, 
      "StringUtil.java");
    this.supportingFiles.add(_supportingFile_11);
    boolean _equals = "joda".equals(this.dateLibrary);
    if (_equals) {
      String _replace_6 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
      SupportingFile _supportingFile_12 = new SupportingFile("JacksonConfig.mustache", _replace_6, 
        "JacksonConfig.java");
      this.supportingFiles.add(_supportingFile_12);
      String _replace_7 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
      SupportingFile _supportingFile_13 = new SupportingFile("JodaDateTimeProvider.mustache", _replace_7, 
        "JodaDateTimeProvider.java");
      this.supportingFiles.add(_supportingFile_13);
      String _replace_8 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
      SupportingFile _supportingFile_14 = new SupportingFile("JodaLocalDateProvider.mustache", _replace_8, "JodaLocalDateProvider.java");
      this.supportingFiles.add(_supportingFile_14);
    } else {
      boolean _startsWith = this.dateLibrary.startsWith("java8");
      if (_startsWith) {
        String _replace_9 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
        SupportingFile _supportingFile_15 = new SupportingFile("OffsetDateTimeProvider.mustache", _replace_9, "OffsetDateTimeProvider.java");
        this.supportingFiles.add(_supportingFile_15);
        String _replace_10 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
        SupportingFile _supportingFile_16 = new SupportingFile("LocalDateProvider.mustache", _replace_10, 
          "LocalDateProvider.java");
        this.supportingFiles.add(_supportingFile_16);
      }
    }
  }
  
  public void addOperationToGroup(final String tag, final String resourcePath, final Operation operation, final ItemisCodegenOperation co, final Map<String, List<ItemisCodegenOperation>> operations) {
    String basePath = resourcePath;
    boolean _startsWith = basePath.startsWith("/");
    if (_startsWith) {
      String _substring = basePath.substring(1);
      basePath = _substring;
    }
    int pos = basePath.indexOf("/");
    if ((pos > 0)) {
      String _substring_1 = basePath.substring(0, pos);
      basePath = _substring_1;
    }
    boolean _equals = Objects.equal(basePath, "");
    if (_equals) {
      basePath = "default";
    } else {
      boolean _startsWith_1 = co.path.startsWith(("/" + basePath));
      if (_startsWith_1) {
        int _length = ("/" + basePath).length();
        String _substring_2 = co.path.substring(_length);
        co.path = _substring_2;
      }
      boolean _isEmpty = co.path.isEmpty();
      boolean _not = (!_isEmpty);
      co.subresourceOperation = Boolean.valueOf(_not);
    }
    List<ItemisCodegenOperation> opList = operations.get(basePath);
    boolean _equals_1 = Objects.equal(opList, null);
    if (_equals_1) {
      ArrayList<ItemisCodegenOperation> _arrayList = new ArrayList<ItemisCodegenOperation>();
      opList = _arrayList;
      operations.put(basePath, opList);
    }
    opList.add(co);
    co.baseName = basePath;
  }
  
  @Override
  public Map<String, Object> postProcessOperations(final Map<String, Object> objs) {
    Map<String, Object> _xblockexpression = null;
    {
      Object _get = objs.get("operations");
      final Map<String, Object> operations = ((Map<String, Object>) _get);
      boolean _notEquals = (!Objects.equal(operations, null));
      if (_notEquals) {
        Object _get_1 = operations.get("operation");
        final List<ItemisCodegenOperation> ops = ((List<ItemisCodegenOperation>) _get_1);
        for (final ItemisCodegenOperation operation : ops) {
          {
            boolean _equals = Objects.equal(operation.hasConsumes, Boolean.TRUE);
            if (_equals) {
              final Map<String, String> firstType = operation.consumes.get(0);
              boolean _notEquals_1 = (!Objects.equal(firstType, null));
              if (_notEquals_1) {
                String _get_2 = firstType.get("mediaType");
                boolean _equals_1 = "multipart/form-data".equals(_get_2);
                if (_equals_1) {
                  operation.isMultipart = Boolean.TRUE;
                }
              }
            }
            final List<CodegenResponse> responses = operation.responses;
            boolean _notEquals_2 = (!Objects.equal(responses, null));
            if (_notEquals_2) {
              for (final CodegenResponse resp : responses) {
                boolean _equals_2 = "0".equals(resp.code);
                if (_equals_2) {
                  resp.code = "200";
                }
              }
            }
            boolean _equals_3 = Objects.equal(operation.returnType, null);
            if (_equals_3) {
              operation.returnType = "Void";
            } else {
              boolean _startsWith = operation.returnType.startsWith("List");
              if (_startsWith) {
                final String rt = operation.returnType;
                final int end = rt.lastIndexOf(">");
                if ((end > 0)) {
                  int _length = "List<".length();
                  String _substring = rt.substring(_length, end);
                  String _trim = _substring.trim();
                  operation.returnType = _trim;
                  operation.returnContainer = "List";
                }
              } else {
                boolean _startsWith_1 = operation.returnType.startsWith("Map");
                if (_startsWith_1) {
                  final String rt_1 = operation.returnType;
                  final int end_1 = rt_1.lastIndexOf(">");
                  if ((end_1 > 0)) {
                    int _length_1 = "Map<".length();
                    String _substring_1 = rt_1.substring(_length_1, end_1);
                    String[] _split = _substring_1.split(",");
                    String _get_3 = _split[1];
                    String _trim_1 = _get_3.trim();
                    operation.returnType = _trim_1;
                    operation.returnContainer = "Map";
                  }
                } else {
                  boolean _startsWith_2 = operation.returnType.startsWith("Set");
                  if (_startsWith_2) {
                    final String rt_2 = operation.returnType;
                    final int end_2 = rt_2.lastIndexOf(">");
                    if ((end_2 > 0)) {
                      int _length_2 = "Set<".length();
                      String _substring_2 = rt_2.substring(_length_2, end_2);
                      String _trim_2 = _substring_2.trim();
                      operation.returnType = _trim_2;
                      operation.returnContainer = "Set";
                    }
                  }
                }
              }
            }
          }
        }
      }
      _xblockexpression = objs;
    }
    return _xblockexpression;
  }
  
  @Override
  public void postProcessModelProperty(final CodegenModel model, final CodegenProperty property) {
    boolean _boolean = BooleanUtils.toBoolean(model.isEnum);
    boolean _not = (!_boolean);
    if (_not) {
      model.imports.add("JsonProperty");
      boolean _boolean_1 = BooleanUtils.toBoolean(model.hasEnums);
      if (_boolean_1) {
        model.imports.add("JsonValue");
      }
    }
  }
  
  @Override
  public Map<String, Object> postProcessModelsEnum(final Map<String, Object> _objs) {
    Map<String, Object> _xblockexpression = null;
    {
      final Map<String, Object> objs = super.postProcessModelsEnum(_objs);
      Object _get = objs.get("imports");
      final List<Map<String, String>> imports = ((List<Map<String, String>>) _get);
      Object _get_1 = objs.get("models");
      final List<Object> models = ((List<Object>) _get_1);
      for (final Object _mo : models) {
        {
          final Map<String, Object> mo = ((Map<String, Object>) _mo);
          Object _get_2 = mo.get("model");
          final CodegenModel cm = ((CodegenModel) _get_2);
          if ((Boolean.TRUE.equals(cm.isEnum) && (!Objects.equal(cm.allowableValues, null)))) {
            String _get_3 = this.importMapping.get("JsonValue");
            cm.imports.add(_get_3);
            final Map<String, String> item = new HashMap<String, String>();
            String _get_4 = this.importMapping.get("JsonValue");
            item.put("import", _get_4);
            imports.add(item);
          }
        }
      }
      _xblockexpression = objs;
    }
    return _xblockexpression;
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
  @Override
  public ItemisCodegenOperation fromOperation(final String path, final String httpMethod, final Operation operation, final Map<String, Model> definitions, final Swagger swagger) {
    final ItemisCodegenOperation op = new ItemisCodegenOperation();
    final Set<String> imports = new HashSet<String>();
    Map<String, Object> _vendorExtensions = operation.getVendorExtensions();
    op.vendorExtensions = _vendorExtensions;
    String operationId = this.getOrGenerateOperationId(operation, path, httpMethod);
    String _removeNonNameElementToCamelCase = this.removeNonNameElementToCamelCase(operationId);
    operationId = _removeNonNameElementToCamelCase;
    op.path = path;
    String _operationId = this.toOperationId(operationId);
    op.operationId = _operationId;
    String _summary = operation.getSummary();
    String _escapeText = this.escapeText(_summary);
    op.summary = _escapeText;
    String _description = operation.getDescription();
    op.unescapedNotes = _description;
    String _description_1 = operation.getDescription();
    String _escapeText_1 = this.escapeText(_description_1);
    op.notes = _escapeText_1;
    List<String> _tags = operation.getTags();
    op.tags = _tags;
    op.hasConsumes = Boolean.valueOf(false);
    op.hasProduces = Boolean.valueOf(false);
    String _lowerCase = httpMethod.toLowerCase();
    op.httpMethodLowerCase = _lowerCase;
    List<String> consumes = new ArrayList<String>();
    List<String> _consumes = operation.getConsumes();
    boolean _notEquals = (!Objects.equal(_consumes, null));
    if (_notEquals) {
      List<String> _consumes_1 = operation.getConsumes();
      int _size = _consumes_1.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        List<String> _consumes_2 = operation.getConsumes();
        consumes = _consumes_2;
      } else {
      }
    } else {
      if ((((!Objects.equal(swagger, null)) && (!Objects.equal(swagger.getConsumes(), null))) && (swagger.getConsumes().size() > 0))) {
        List<String> _consumes_3 = swagger.getConsumes();
        consumes = _consumes_3;
        List<String> _consumes_4 = swagger.getConsumes();
        String _plus = ("No consumes defined in operation. Using global consumes (" + _consumes_4);
        String _plus_1 = (_plus + ") for ");
        String _plus_2 = (_plus_1 + 
          op.operationId);
        ItemisServerMainCodegen.LOGGER.debug(_plus_2);
      }
    }
    if (((!Objects.equal(consumes, null)) && (consumes.size() > 0))) {
      final List<Map<String, String>> c = new ArrayList<Map<String, String>>();
      int count = 0;
      for (final String key : consumes) {
        {
          final Map<String, String> mediaType = new HashMap<String, String>();
          String _escapeQuotationMark = this.escapeQuotationMark(key);
          String _escapeText_2 = this.escapeText(_escapeQuotationMark);
          mediaType.put("mediaType", _escapeText_2);
          int _count = count;
          count = (_count + 1);
          int _size_1 = consumes.size();
          boolean _lessThan = (count < _size_1);
          if (_lessThan) {
            mediaType.put("hasMore", "true");
          } else {
            mediaType.put("hasMore", null);
          }
          c.add(mediaType);
        }
      }
      op.consumes = c;
      op.hasConsumes = Boolean.valueOf(true);
    }
    List<String> produces = new ArrayList<String>();
    List<String> _produces = operation.getProduces();
    boolean _notEquals_1 = (!Objects.equal(_produces, null));
    if (_notEquals_1) {
      List<String> _produces_1 = operation.getProduces();
      int _size_1 = _produces_1.size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        List<String> _produces_2 = operation.getProduces();
        produces = _produces_2;
      } else {
      }
    } else {
      if ((((!Objects.equal(swagger, null)) && (!Objects.equal(swagger.getProduces(), null))) && (swagger.getProduces().size() > 0))) {
        List<String> _produces_3 = swagger.getProduces();
        produces = _produces_3;
        List<String> _produces_4 = swagger.getProduces();
        String _plus_3 = ("No produces defined in operation. Using global produces (" + _produces_4);
        String _plus_4 = (_plus_3 + ") for ");
        String _plus_5 = (_plus_4 + 
          op.operationId);
        ItemisServerMainCodegen.LOGGER.debug(_plus_5);
      }
    }
    if (((!Objects.equal(produces, null)) && (produces.size() > 0))) {
      final List<Map<String, String>> c_1 = new ArrayList<Map<String, String>>();
      int count_1 = 0;
      for (final String key_1 : produces) {
        {
          final Map<String, String> mediaType = new HashMap<String, String>();
          String _escapeQuotationMark = this.escapeQuotationMark(key_1);
          String _escapeText_2 = this.escapeText(_escapeQuotationMark);
          mediaType.put("mediaType", _escapeText_2);
          int _count = count_1;
          count_1 = (_count + 1);
          int _size_2 = produces.size();
          boolean _lessThan = (count_1 < _size_2);
          if (_lessThan) {
            mediaType.put("hasMore", "true");
          } else {
            mediaType.put("hasMore", null);
          }
          c_1.add(mediaType);
        }
      }
      op.produces = c_1;
      op.hasProduces = Boolean.valueOf(true);
    }
    if (((!Objects.equal(operation.getResponses(), null)) && (!operation.getResponses().isEmpty()))) {
      Map<String, Response> _responses = operation.getResponses();
      final Response methodResponse = this.findMethodResponse(_responses);
      Map<String, Response> _responses_1 = operation.getResponses();
      Set<Map.Entry<String, Response>> _entrySet = _responses_1.entrySet();
      for (final Map.Entry<String, Response> entry : _entrySet) {
        {
          final Response response = entry.getValue();
          String _key = entry.getKey();
          final CodegenResponse r = this.fromResponse(_key, response);
          r.hasMore = Boolean.valueOf(true);
          if ((((!Objects.equal(r.baseType, null)) && (!this.defaultIncludes.contains(r.baseType))) && 
            (!this.languageSpecificPrimitives.contains(r.baseType)))) {
            imports.add(r.baseType);
          }
          boolean _equals = Objects.equal(response, methodResponse);
          r.isDefault = Boolean.valueOf(_equals);
          op.responses.add(r);
          if (((r.isBinary).booleanValue() && (r.isDefault).booleanValue())) {
            op.isResponseBinary = Boolean.TRUE;
          }
        }
      }
      int _size_2 = op.responses.size();
      int _minus = (_size_2 - 1);
      CodegenResponse _get = op.responses.get(_minus);
      _get.hasMore = Boolean.valueOf(false);
      boolean _notEquals_2 = (!Objects.equal(methodResponse, null));
      if (_notEquals_2) {
        Property _schema = methodResponse.getSchema();
        boolean _notEquals_3 = (!Objects.equal(_schema, null));
        if (_notEquals_3) {
          Property _schema_1 = methodResponse.getSchema();
          final CodegenProperty cm = this.fromProperty("response", _schema_1);
          final Property responseProperty = methodResponse.getSchema();
          if ((responseProperty instanceof ArrayProperty)) {
            final ArrayProperty ap = ((ArrayProperty) responseProperty);
            Property _items = ap.getItems();
            final CodegenProperty innerProperty = this.fromProperty("response", _items);
            op.returnBaseType = innerProperty.baseType;
          } else {
            boolean _notEquals_4 = (!Objects.equal(cm.complexType, null));
            if (_notEquals_4) {
              op.returnBaseType = cm.complexType;
            } else {
              op.returnBaseType = cm.baseType;
            }
          }
          ExampleGenerator _exampleGenerator = new ExampleGenerator(definitions);
          Map<String, Object> _examples = methodResponse.getExamples();
          List<String> _produces_5 = operation.getProduces();
          List<Map<String, String>> _generate = _exampleGenerator.generate(_examples, _produces_5, responseProperty);
          op.examples = _generate;
          String _defaultValue = this.toDefaultValue(responseProperty);
          op.defaultResponse = _defaultValue;
          op.returnType = cm.datatype;
          op.hasReference = Boolean.valueOf(((!Objects.equal(definitions, null)) && definitions.containsKey(op.returnBaseType)));
          boolean _notEquals_5 = (!Objects.equal(definitions, null));
          if (_notEquals_5) {
            final Model m = definitions.get(op.returnBaseType);
            boolean _notEquals_6 = (!Objects.equal(m, null));
            if (_notEquals_6) {
              final CodegenModel cmod = this.fromModel(op.returnBaseType, m, definitions);
              op.discriminator = cmod.discriminator;
            }
          }
          boolean _notEquals_7 = (!Objects.equal(cm.isContainer, null));
          if (_notEquals_7) {
            op.returnContainer = cm.containerType;
            boolean _equals = "map".equals(cm.containerType);
            if (_equals) {
              op.isMapContainer = Boolean.TRUE;
            } else {
              boolean _equalsIgnoreCase = "list".equalsIgnoreCase(cm.containerType);
              if (_equalsIgnoreCase) {
                op.isListContainer = Boolean.TRUE;
              } else {
                boolean _equalsIgnoreCase_1 = "array".equalsIgnoreCase(cm.containerType);
                if (_equalsIgnoreCase_1) {
                  op.isListContainer = Boolean.TRUE;
                }
              }
            }
          } else {
            op.returnSimpleType = Boolean.valueOf(true);
          }
          if ((this.languageSpecificPrimitives().contains(op.returnBaseType) || Objects.equal(op.returnBaseType, null))) {
            op.returnTypeIsPrimitive = Boolean.valueOf(true);
          }
        }
        this.addHeaders(methodResponse, op.responseHeaders);
      }
    }
    final List<Parameter> parameters = operation.getParameters();
    CodegenParameter bodyParam = null;
    final List<CodegenParameter> allParams = new ArrayList<CodegenParameter>();
    final List<CodegenParameter> bodyParams = new ArrayList<CodegenParameter>();
    final List<CodegenParameter> pathParams = new ArrayList<CodegenParameter>();
    final List<CodegenParameter> queryParams = new ArrayList<CodegenParameter>();
    final List<CodegenParameter> headerParams = new ArrayList<CodegenParameter>();
    final List<CodegenParameter> cookieParams = new ArrayList<CodegenParameter>();
    final List<CodegenParameter> formParams = new ArrayList<CodegenParameter>();
    boolean _notEquals_8 = (!Objects.equal(parameters, null));
    if (_notEquals_8) {
      for (final Parameter param : parameters) {
        {
          final CodegenParameter p = this.fromParameter(param, imports);
          if ((this.ensureUniqueParams).booleanValue()) {
            String _enforceUniqueParams = this.enforceUniqueParams(p, allParams);
            p.paramName = _enforceUniqueParams;
          }
          allParams.add(p);
          if ((param instanceof QueryParameter)) {
            CodegenParameter _copy = p.copy();
            queryParams.add(_copy);
          } else {
            if ((param instanceof PathParameter)) {
              CodegenParameter _copy_1 = p.copy();
              pathParams.add(_copy_1);
            } else {
              if ((param instanceof HeaderParameter)) {
                CodegenParameter _copy_2 = p.copy();
                headerParams.add(_copy_2);
              } else {
                if ((param instanceof CookieParameter)) {
                  CodegenParameter _copy_3 = p.copy();
                  cookieParams.add(_copy_3);
                } else {
                  if ((param instanceof BodyParameter)) {
                    bodyParam = p;
                    CodegenParameter _copy_4 = p.copy();
                    bodyParams.add(_copy_4);
                  } else {
                    if ((param instanceof FormParameter)) {
                      CodegenParameter _copy_5 = p.copy();
                      formParams.add(_copy_5);
                    }
                  }
                }
              }
            }
          }
          if ((Objects.equal(p.required, null) || (!(p.required).booleanValue()))) {
            op.hasOptionalParams = Boolean.valueOf(true);
          }
        }
      }
    }
    for (final String i : imports) {
      boolean _needToImport = this.needToImport(i);
      if (_needToImport) {
        op.imports.add(i);
      }
    }
    op.bodyParam = bodyParam;
    String _upperCase = httpMethod.toUpperCase();
    op.httpMethod = _upperCase;
    if ((this.sortParamsByRequiredFlag).booleanValue()) {
      Collections.<CodegenParameter>sort(allParams, new Comparator<CodegenParameter>() {
        @Override
        public int compare(final CodegenParameter one, final CodegenParameter another) {
          boolean oneRequired = false;
          boolean _notEquals = (!Objects.equal(one.required, null));
          if (_notEquals) {
            oneRequired = (one.required).booleanValue();
          }
          boolean anotherRequired = false;
          boolean _notEquals_1 = (!Objects.equal(another.required, null));
          if (_notEquals_1) {
            anotherRequired = (another.required).booleanValue();
          }
          if ((oneRequired == anotherRequired)) {
            return 0;
          } else {
            if (oneRequired) {
              return (-1);
            } else {
              return 1;
            }
          }
        }
      });
    }
    List<CodegenParameter> _addHasMore = ItemisServerMainCodegen.addHasMore(allParams);
    op.allParams = _addHasMore;
    List<CodegenParameter> _addHasMore_1 = ItemisServerMainCodegen.addHasMore(bodyParams);
    op.bodyParams = _addHasMore_1;
    List<CodegenParameter> _addHasMore_2 = ItemisServerMainCodegen.addHasMore(pathParams);
    op.pathParams = _addHasMore_2;
    List<CodegenParameter> _addHasMore_3 = ItemisServerMainCodegen.addHasMore(queryParams);
    op.queryParams = _addHasMore_3;
    List<CodegenParameter> _addHasMore_4 = ItemisServerMainCodegen.addHasMore(headerParams);
    op.headerParams = _addHasMore_4;
    List<CodegenParameter> _addHasMore_5 = ItemisServerMainCodegen.addHasMore(formParams);
    op.formParams = _addHasMore_5;
    op.nickname = op.operationId;
    int _size_3 = op.allParams.size();
    boolean _greaterThan_2 = (_size_3 > 0);
    if (_greaterThan_2) {
      op.hasParams = Boolean.valueOf(true);
    }
    ExternalDocs _externalDocs = operation.getExternalDocs();
    op.externalDocs = _externalDocs;
    boolean _isRestfulShow = op.isRestfulShow();
    op.isRestfulShow = Boolean.valueOf(_isRestfulShow);
    boolean _isRestfulIndex = op.isRestfulIndex();
    op.isRestfulIndex = Boolean.valueOf(_isRestfulIndex);
    boolean _isRestfulCreate = op.isRestfulCreate();
    op.isRestfulCreate = Boolean.valueOf(_isRestfulCreate);
    boolean _isRestfulUpdate = op.isRestfulUpdate();
    op.isRestfulUpdate = Boolean.valueOf(_isRestfulUpdate);
    boolean _isRestfulDestroy = op.isRestfulDestroy();
    op.isRestfulDestroy = Boolean.valueOf(_isRestfulDestroy);
    boolean _isRestful = op.isRestful();
    op.isRestful = Boolean.valueOf(_isRestful);
    String _sanitizePath = this.sanitizePath(op.path);
    op.path = _sanitizePath;
    return op;
  }
  
  public String enforceUniqueParams(final CodegenParameter p, final List<CodegenParameter> allParams) {
    String _xblockexpression = null;
    {
      for (final CodegenParameter cp : allParams) {
        boolean _equals = p.paramName.equals(cp.paramName);
        if (_equals) {
          return ItemisServerMainCodegen.generateNextName(p.paramName);
        }
      }
      _xblockexpression = p.paramName;
    }
    return _xblockexpression;
  }
  
  private void addHeaders(final Response response, final List<CodegenProperty> target) {
    Map<String, Property> _headers = response.getHeaders();
    boolean _notEquals = (!Objects.equal(_headers, null));
    if (_notEquals) {
      Map<String, Property> _headers_1 = response.getHeaders();
      Set<Map.Entry<String, Property>> _entrySet = _headers_1.entrySet();
      for (final Map.Entry<String, Property> headers : _entrySet) {
        String _key = headers.getKey();
        Property _value = headers.getValue();
        CodegenProperty _fromProperty = this.fromProperty(_key, _value);
        target.add(_fromProperty);
      }
    }
  }
  
  private static String generateNextName(final String name) {
    final Pattern pattern = Pattern.compile("\\d+\\z");
    final Matcher matcher = pattern.matcher(name);
    boolean _find = matcher.find();
    if (_find) {
      final String numStr = matcher.group();
      int _parseInt = Integer.parseInt(numStr);
      final int num = (_parseInt + 1);
      int _length = name.length();
      int _length_1 = numStr.length();
      int _minus = (_length - _length_1);
      String _substring = name.substring(0, _minus);
      return (_substring + Integer.valueOf(num));
    } else {
      return (name + "2");
    }
  }
  
  private static List<CodegenParameter> addHasMore(final List<CodegenParameter> objs) {
    boolean _notEquals = (!Objects.equal(objs, null));
    if (_notEquals) {
      for (int i = 0; (i < objs.size()); i++) {
        {
          if ((i > 0)) {
            CodegenParameter _get = objs.get(i);
            Boolean _boolean = new Boolean(true);
            _get.secondaryParam = _boolean;
          }
          int _size = objs.size();
          int _minus = (_size - 1);
          boolean _lessThan = (i < _minus);
          if (_lessThan) {
            CodegenParameter _get_1 = objs.get(i);
            Boolean _boolean_1 = new Boolean(true);
            _get_1.hasMore = _boolean_1;
          }
        }
      }
    }
    return objs;
  }
  
  private String sanitizePath(final String p) {
    return p.replaceAll("\'", "%22");
  }
}
