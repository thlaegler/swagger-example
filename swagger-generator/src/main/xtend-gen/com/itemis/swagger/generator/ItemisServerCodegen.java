package com.itemis.swagger.generator;

import com.itemis.swagger.generator.ItemisCodegenOperation;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.languages.JavaResteasyServerCodegen;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Swagger;
import java.io.File;
import java.util.Map;
import javax.annotation.Generated;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
@Generated("org.eclipse.xtend.core.compiler.XtendGenerator")
public class ItemisServerCodegen extends JavaResteasyServerCodegen {
  public ItemisServerCodegen() {
    super();
    this.artifactId = "swagger-example-api";
    this.outputFolder = "generated-code/itemis-swagger";
    this.apiTemplateFiles.put("apiService.mustache", ".java");
    this.apiTemplateFiles.put("apiServiceImpl.mustache", ".java");
    this.apiTemplateFiles.put("apiServiceFactory.mustache", ".java");
    this.apiTestTemplateFiles.clear();
    this.apiTestTemplateFiles.put("apiIntegrationTest.mustache", ".java");
    this.apiTestTemplateFiles.put("apiCucumberFeatureSteps.mustache", ".java");
    this.apiTestTemplateFiles.put("apiCucumberFeature.mustache", ".feature");
    this.modelDocTemplateFiles.remove("model_doc.mustache");
    this.apiDocTemplateFiles.remove("api_doc.mustache");
    this.dateLibrary = "legacy";
    this.embeddedTemplateDir = (this.templateDir = (("JavaJaxRS" + File.separator) + "resteasy"));
  }
  
  @Override
  public String getName() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("itemis-swagger");
    return _builder.toString();
  }
  
  @Override
  public String getHelp() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Generates an Example Java JAXRS-Resteasy Server application.");
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
    SupportingFile _supportingFile_4 = new SupportingFile("web.mustache", "src/main/webapp/WEB-INF", "web.xml");
    this.writeOptional(this.outputFolder, _supportingFile_4);
    SupportingFile _supportingFile_5 = new SupportingFile("jboss-web.mustache", "src/main/webapp/WEB-INF", "jboss-web.xml");
    this.writeOptional(this.outputFolder, _supportingFile_5);
    String _replace = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
    SupportingFile _supportingFile_6 = new SupportingFile("RestApplication.mustache", _replace, 
      "RestApplication.java");
    this.writeOptional(this.outputFolder, _supportingFile_6);
    String _replace_1 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_7 = new SupportingFile("ApiException.mustache", _replace_1, 
      "ApiException.java");
    String _replace_2 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_8 = new SupportingFile("ApiOriginFilter.mustache", _replace_2, 
      "ApiOriginFilter.java");
    String _replace_3 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_9 = new SupportingFile("ApiResponseMessage.mustache", _replace_3, 
      "ApiResponseMessage.java");
    String _replace_4 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_10 = new SupportingFile("NotFoundException.mustache", _replace_4, 
      "NotFoundException.java");
    String _replace_5 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
    SupportingFile _supportingFile_11 = new SupportingFile("StringUtil.mustache", _replace_5, 
      "StringUtil.java");
    String _replace_6 = ((this.testFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_12 = new SupportingFile("CucumberTest.mustache", _replace_6, 
      "CucumberTest.java");
    String _replace_7 = ((this.testFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_13 = new SupportingFile("AbstractIntegrationTest.mustache", _replace_7, 
      "AbstractIntegrationTest.java");
    String _replace_8 = ((this.testFolder + "/") + this.apiPackage).replace(".", "/");
    SupportingFile _supportingFile_14 = new SupportingFile("AbstractCucumberFeatureSteps.mustache", _replace_8, "AbstractFeatureSteps.java");
    CollectionExtensions.<SupportingFile>addAll(this.supportingFiles, _supportingFile_7, _supportingFile_8, _supportingFile_9, _supportingFile_10, _supportingFile_11, _supportingFile_12, _supportingFile_13, _supportingFile_14);
    boolean _equals = "joda".equals(this.dateLibrary);
    if (_equals) {
      String _replace_9 = ((this.sourceFolder + "/") + this.invokerPackage).replace(".", "/");
      SupportingFile _supportingFile_15 = new SupportingFile("JacksonConfig.mustache", _replace_9, 
        "JacksonConfig.java");
      String _replace_10 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
      SupportingFile _supportingFile_16 = new SupportingFile("JodaDateTimeProvider.mustache", _replace_10, 
        "JodaDateTimeProvider.java");
      String _replace_11 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
      SupportingFile _supportingFile_17 = new SupportingFile("JodaLocalDateProvider.mustache", _replace_11, "JodaLocalDateProvider.java");
      CollectionExtensions.<SupportingFile>addAll(this.supportingFiles, _supportingFile_15, _supportingFile_16, _supportingFile_17);
    } else {
      boolean _startsWith = this.dateLibrary.startsWith("java8");
      if (_startsWith) {
        String _replace_12 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
        SupportingFile _supportingFile_18 = new SupportingFile("OffsetDateTimeProvider.mustache", _replace_12, "OffsetDateTimeProvider.java");
        String _replace_13 = ((this.sourceFolder + "/") + this.apiPackage).replace(".", "/");
        SupportingFile _supportingFile_19 = new SupportingFile("LocalDateProvider.mustache", _replace_13, 
          "LocalDateProvider.java");
        CollectionExtensions.<SupportingFile>addAll(this.supportingFiles, _supportingFile_18, _supportingFile_19);
      }
    }
  }
  
  @Override
  public String apiTestFilename(final String templateName, final String tag) {
    String _xblockexpression = null;
    {
      String result = super.apiTestFilename(templateName, tag);
      boolean _endsWith = templateName.endsWith("IntegrationTest.mustache");
      if (_endsWith) {
        final int ix = result.lastIndexOf("/");
        String _replace = result.replace("Test", "");
        result = _replace;
        String _substring = result.substring(0, ix);
        String _plus = (_substring + "/integration/");
        String _initialCaps = this.initialCaps(tag);
        String _plus_1 = (_plus + _initialCaps);
        String _plus_2 = (_plus_1 + "ApiIntegrationTest.java");
        result = _plus_2;
        String _apiFileFolder = this.apiFileFolder();
        String _apiPackage = this.apiPackage();
        String _replace_1 = _apiPackage.replace(".", "/");
        String _plus_3 = ((this.testFolder + "/") + _replace_1);
        String _replace_2 = result.replace(_apiFileFolder, _plus_3);
        result = _replace_2;
      } else {
        boolean _endsWith_1 = templateName.endsWith("CucumberFeature.mustache");
        if (_endsWith_1) {
          final int ix_1 = result.lastIndexOf("/");
          String _substring_1 = result.substring(0, ix_1);
          String _plus_4 = (_substring_1 + "/feature/");
          String _plus_5 = (_plus_4 + tag);
          String _plus_6 = (_plus_5 + "/");
          String _plus_7 = (_plus_6 + tag);
          String _plus_8 = (_plus_7 + ".feature");
          result = _plus_8;
          String _apiFileFolder_1 = this.apiFileFolder();
          String _replace_3 = result.replace(_apiFileFolder_1, (this.testFolder + "/feature"));
          result = _replace_3;
        } else {
          boolean _endsWith_2 = templateName.endsWith("CucumberFeatureSteps.mustache");
          if (_endsWith_2) {
            final int ix_2 = result.lastIndexOf("/");
            String _replace_4 = result.replace("Test", "");
            result = _replace_4;
            String _substring_2 = result.substring(0, ix_2);
            String _plus_9 = (_substring_2 + "/feature/");
            String _plus_10 = (_plus_9 + tag);
            String _plus_11 = (_plus_10 + "/");
            String _initialCaps_1 = this.initialCaps(tag);
            String _plus_12 = (_plus_11 + _initialCaps_1);
            String _plus_13 = (_plus_12 + "ApiFeatureSteps.java");
            result = _plus_13;
            String _apiFileFolder_2 = this.apiFileFolder();
            String _apiPackage_1 = this.apiPackage();
            String _replace_5 = _apiPackage_1.replace(".", "/");
            String _plus_14 = ((this.testFolder + "/") + _replace_5);
            String _replace_6 = result.replace(_apiFileFolder_2, _plus_14);
            result = _replace_6;
          }
        }
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  @Override
  public ItemisCodegenOperation fromOperation(final String path, final String httpMethod, final Operation operation, final Map<String, Model> definitions, final Swagger swagger) {
    ItemisCodegenOperation _xblockexpression = null;
    {
      final CodegenOperation op = super.fromOperation(path, httpMethod, operation, definitions, swagger);
      ItemisCodegenOperation _itemisCodegenOperation = new ItemisCodegenOperation();
      final Procedure1<ItemisCodegenOperation> _function = (ItemisCodegenOperation it) -> {
        String _lowerCase = httpMethod.toLowerCase();
        it.httpMethodLowerCase = _lowerCase;
        String _description = operation.getDescription();
        it.description = _description;
        it.allParams = op.allParams;
        it.authMethods = op.authMethods;
        it.baseName = op.baseName;
        it.bodyParam = op.bodyParam;
        it.bodyParams = op.bodyParams;
        it.consumes = op.consumes;
        it.defaultResponse = op.defaultResponse;
        it.discriminator = op.discriminator;
        it.examples = op.examples;
        it.externalDocs = op.externalDocs;
        it.formParams = op.formParams;
        it.hasAuthMethods = op.hasAuthMethods;
        it.hasConsumes = op.hasConsumes;
        it.hasMore = op.hasMore;
        it.hasOptionalParams = op.hasOptionalParams;
        it.hasParams = op.hasParams;
        it.hasProduces = op.hasProduces;
        it.hasReference = op.hasReference;
        it.headerParams = op.headerParams;
        it.httpMethod = op.httpMethod;
        it.imports = op.imports;
        it.isListContainer = op.isListContainer;
        it.isMapContainer = op.isMapContainer;
        it.isMultipart = op.isMultipart;
        it.isResponseBinary = op.isResponseBinary;
        it.nickname = op.nickname;
        it.notes = op.notes;
        it.operationId = op.operationId;
        it.operationIdLowerCase = op.operationIdLowerCase;
        it.path = op.path;
        it.pathParams = op.pathParams;
        it.produces = op.produces;
        it.queryParams = op.queryParams;
        it.responses = op.responses;
        it.returnBaseType = op.returnBaseType;
        it.returnContainer = op.returnContainer;
        it.returnSimpleType = op.returnSimpleType;
        it.returnType = op.returnType;
        it.returnTypeIsPrimitive = op.returnTypeIsPrimitive;
        it.subresourceOperation = op.subresourceOperation;
        it.summary = op.summary;
        it.tags = op.tags;
        it.unescapedNotes = op.unescapedNotes;
        it.vendorExtensions = op.vendorExtensions;
      };
      final ItemisCodegenOperation iop = ObjectExtensions.<ItemisCodegenOperation>operator_doubleArrow(_itemisCodegenOperation, _function);
      String _sanitizePath = this.sanitizePath(iop.path);
      iop.path = _sanitizePath;
      _xblockexpression = iop;
    }
    return _xblockexpression;
  }
  
  private String sanitizePath(final String p) {
    return p.replaceAll("\"", "%22");
  }
}
