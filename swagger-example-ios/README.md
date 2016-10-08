# IO.Swagger - the C# library for the Example Rest API

This is the Example Rest API 

This C# SDK is automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen) project:

- API version: 0.0.1-SNAPSHOT
- SDK version: 1.0.0
- Build date: 2016-10-03T14:30:58.236+02:00
- Build package: class io.swagger.codegen.languages.CSharpClientCodegen
    For more information, please visit [http://www.xn- -lgler-gra.com](http://www.xn- -lgler-gra.com)

## Frameworks supported
- .NET 4.0 or later
- Windows Phone 7.1 (Mango)

## Dependencies
- [RestSharp](https://www.nuget.org/packages/RestSharp) - 105.1.0 or later
- [Json.NET](https://www.nuget.org/packages/Newtonsoft.Json/) - 7.0.0 or later

The DLLs included in the package may not be the latest version. We recommned using [NuGet] (https://docs.nuget.org/consume/installing-nuget) to obtain the latest version of the packages:
```
Install-Package RestSharp
Install-Package Newtonsoft.Json
```

NOTE: RestSharp versions greater than 105.1.0 have a bug which causes file uploads to fail. See [RestSharp#742](https://github.com/restsharp/RestSharp/issues/742)

## Installation
Run the following command to generate the DLL
- [Mac/Linux] `/bin/sh build.sh`
- [Windows] `build.bat`

Then include the DLL (under the `bin` folder) in the C# project, and use the namespaces:
```csharp
using IO.Swagger.Api;
using IO.Swagger.Client;
using Model;
```

## Getting Started

```csharp
using System;
using System.Diagnostics;
using IO.Swagger.Api;
using IO.Swagger.Client;
using Model;

namespace Example
{
    public class Example
    {
        public void main()
        {
            
            // Configure OAuth2 access token for authorization: oauth
            Configuration.Default.AccessToken = "YOUR_ACCESS_TOKEN";

            var apiInstance = new SomethingApi();
            var body = new Something(); // Something | Something object that needs to be added to database. 

            try
            {
                // Add a new Something
                Something result = apiInstance.Add(body);
                Debug.WriteLine(result);
            }
            catch (Exception e)
            {
                Debug.Print("Exception when calling SomethingApi.Add: " + e.Message );
            }
        }
    }
}
```

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/example*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*SomethingApi* | [**Add**](docs/SomethingApi.md#add) | **POST** /somethings | Add a new Something
*SomethingApi* | [**FindAll**](docs/SomethingApi.md#findall) | **GET** /somethings | Get all existing Somethings
*SomethingApi* | [**FindById**](docs/SomethingApi.md#findbyid) | **GET** /somethings/{id} | Find an existing Something
*SomethingApi* | [**Remove**](docs/SomethingApi.md#remove) | **DELETE** /somethings | Remove an existing Something
*SomethingApi* | [**Update**](docs/SomethingApi.md#update) | **PUT** /somethings | Update an existing Something


<a name="documentation-for-models"></a>
## Documentation for Models

 - [Model.Error](docs/Error.md)
 - [Model.OtherObject](docs/OtherObject.md)
 - [Model.Something](docs/Something.md)


## Documentation for Authorization

### oauth

- **Type**: OAuth
- **Flow**: implicit
- **Authorization URL**: https://v22015123236530736.hotsrv.de/example/rest/auth
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
