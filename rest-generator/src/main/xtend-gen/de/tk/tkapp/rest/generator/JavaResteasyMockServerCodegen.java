package de.tk.tkapp.rest.generator;

import com.google.common.base.Objects;
import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.CodegenResponse;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.languages.JavaResteasyServerCodegen;
import io.swagger.models.Operation;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.commons.lang3.BooleanUtils;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
@Generated("org.eclipse.xtend.core.compiler.XtendGenerator")
public class JavaResteasyMockServerCodegen extends JavaResteasyServerCodegen {
  public JavaResteasyMockServerCodegen() {
    super();
    this.artifactId = "swagger-jaxrs-resteasy-server";
    this.outputFolder = "generated-code/javaJaxRSMock";
    this.apiTemplateFiles.put("apiService.mustache", ".java");
    this.apiTemplateFiles.put("apiServiceImpl.mustache", ".java");
    this.apiTemplateFiles.put("apiServiceFactory.mustache", ".java");
    this.apiTestTemplateFiles.clear();
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
    String _replace = ((this.sourceFolder + "/") + this.modelPackage).replace(".", "/");
    SupportingFile _supportingFile_4 = new SupportingFile("modelBuilder.mustache", _replace, "Builder.java");
    this.supportingFiles.add(_supportingFile_4);
    String _replace_1 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_5 = new SupportingFile("ApiException.mustache", _replace_1, 
      "ApiException.java");
    this.supportingFiles.add(_supportingFile_5);
    String _replace_2 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_6 = new SupportingFile("ApiOriginFilter.mustache", _replace_2, 
      "ApiOriginFilter.java");
    this.supportingFiles.add(_supportingFile_6);
    String _replace_3 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_7 = new SupportingFile("ApiResponseMessage.mustache", _replace_3, 
      "ApiResponseMessage.java");
    this.supportingFiles.add(_supportingFile_7);
    String _replace_4 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_8 = new SupportingFile("NotFoundException.mustache", _replace_4, 
      "NotFoundException.java");
    this.supportingFiles.add(_supportingFile_8);
    SupportingFile _supportingFile_9 = new SupportingFile("web.mustache", "src/main/webapp/WEB-INF", "web.xml");
    this.writeOptional(this.outputFolder, _supportingFile_9);
    SupportingFile _supportingFile_10 = new SupportingFile("jboss-web.mustache", "src/main/webapp/WEB-INF", "jboss-web.xml");
    this.writeOptional(this.outputFolder, _supportingFile_10);
    String _replace_5 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
    SupportingFile _supportingFile_11 = new SupportingFile("RestApplication.mustache", _replace_5, 
      "RestApplication.java");
    this.writeOptional(this.outputFolder, _supportingFile_11);
    String _replace_6 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
    SupportingFile _supportingFile_12 = new SupportingFile("StringUtil.mustache", _replace_6, 
      "StringUtil.java");
    this.supportingFiles.add(_supportingFile_12);
    boolean _equals = "joda".equals(this.dateLibrary);
    if (_equals) {
      String _replace_7 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
      SupportingFile _supportingFile_13 = new SupportingFile("JacksonConfig.mustache", _replace_7, 
        "JacksonConfig.java");
      this.supportingFiles.add(_supportingFile_13);
      String _replace_8 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
      SupportingFile _supportingFile_14 = new SupportingFile("JodaDateTimeProvider.mustache", _replace_8, 
        "JodaDateTimeProvider.java");
      this.supportingFiles.add(_supportingFile_14);
      String _replace_9 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
      SupportingFile _supportingFile_15 = new SupportingFile("JodaLocalDateProvider.mustache", _replace_9, "JodaLocalDateProvider.java");
      this.supportingFiles.add(_supportingFile_15);
    } else {
      boolean _startsWith = this.dateLibrary.startsWith("java8");
      if (_startsWith) {
        String _replace_10 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
        SupportingFile _supportingFile_16 = new SupportingFile("OffsetDateTimeProvider.mustache", _replace_10, "OffsetDateTimeProvider.java");
        this.supportingFiles.add(_supportingFile_16);
        String _replace_11 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
        SupportingFile _supportingFile_17 = new SupportingFile("LocalDateProvider.mustache", _replace_11, 
          "LocalDateProvider.java");
        this.supportingFiles.add(_supportingFile_17);
      }
    }
  }
  
  @Override
  public void addOperationToGroup(final String tag, final String resourcePath, final Operation operation, final CodegenOperation co, final Map<String, List<CodegenOperation>> operations) {
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
    List<CodegenOperation> opList = operations.get(basePath);
    boolean _equals_1 = Objects.equal(opList, null);
    if (_equals_1) {
      ArrayList<CodegenOperation> _arrayList = new ArrayList<CodegenOperation>();
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
        final List<CodegenOperation> ops = ((List<CodegenOperation>) _get_1);
        for (final CodegenOperation operation : ops) {
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
}
