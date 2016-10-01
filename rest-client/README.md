# rest-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.laegler.rest</groupId>
    <artifactId>rest-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.laegler.rest:rest-client:0.0.1-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/rest-client-0.0.1-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.laegler.rest.api.invoker.*;
import com.laegler.rest.api.invoker.auth.*;
import com.laegler.rest.api.invoker.model.*;
import com.laegler.rest.api.handler.ExampleApi;

import java.io.File;
import java.util.*;

public class ExampleApiExample {

    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        
        // Configure OAuth2 access token for authorization: oauth
        OAuth oauth = (OAuth) defaultClient.getAuthentication("oauth");
        oauth.setAccessToken("YOUR ACCESS TOKEN");

        ExampleApi apiInstance = new ExampleApi();
        Something body = new Something(); // Something | Something object that needs to be added to database. 
        try {
            Something result = apiInstance.add(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExampleApi#add");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://v22015123236530736.hotsrv.de/example*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ExampleApi* | [**add**](docs/ExampleApi.md#add) | **POST** /somethings | Add a new Something
*ExampleApi* | [**findAll**](docs/ExampleApi.md#findAll) | **GET** /somethings | Get all existing Somethings
*ExampleApi* | [**findById**](docs/ExampleApi.md#findById) | **GET** /somethings/{id} | Find an existing Something
*ExampleApi* | [**remove**](docs/ExampleApi.md#remove) | **DELETE** /somethings | Remove an existing Something
*ExampleApi* | [**update**](docs/ExampleApi.md#update) | **PUT** /somethings | Update an existing Something
*SomethingApi* | [**add**](docs/SomethingApi.md#add) | **POST** /somethings | Add a new Something
*SomethingApi* | [**findAll**](docs/SomethingApi.md#findAll) | **GET** /somethings | Get all existing Somethings
*SomethingApi* | [**findById**](docs/SomethingApi.md#findById) | **GET** /somethings/{id} | Find an existing Something
*SomethingApi* | [**remove**](docs/SomethingApi.md#remove) | **DELETE** /somethings | Remove an existing Something
*SomethingApi* | [**update**](docs/SomethingApi.md#update) | **PUT** /somethings | Update an existing Something


## Documentation for Models

 - [Error](docs/Error.md)
 - [OtherObject](docs/OtherObject.md)
 - [Something](docs/Something.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### oauth

- **Type**: OAuth
- **Flow**: implicit
- **Authorizatoin URL**: https://v22015123236530736.hotsrv.de/example/rest/auth
- **Scopes**: 
  - basic: to read any and all data related to a user (e.g. following/followed-by
 lists, photos, etc.) (granted by default)

  - comments: to create or delete comments on a user’s behalf
  - relationships: to follow and unfollow users on a user’s behalf
  - likes: to like and unlike items on a user’s behalf

### key

- **Type**: API key
- **API key parameter name**: access_token
- **Location**: URL query string


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

thomas.laegler@googlemail.com

