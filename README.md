# Itemis Swagger Example REST API Parent Project

Example project, which uses Swagger, Xtend, some Maven plugins and 'mvn generate-sources site' to generate REST Projects (Java REST API, Java REST client, Android REST client and iOS client) and documentation (REST API Doc, JavaDoc, Maven Site, DocBook, PDF etc.) from YAML.

## Usage

* Define your REST API: /rest-generate/yaml/api.yaml
* Build projects including documentation:
		
	mvn install site
* Run generated WAR (/swagger-example-api/target/swagger-example-api.war) on Server (JBoss, Tomcat, Jetty etc.)
* Have a look at generated API documentation: http://localhost:8080/example/index.html
* Develop against your REST API: http://localhost:8080/example/rest/somethings
* Run and test your REST API in Docker image:

	mvn install -Pdocker