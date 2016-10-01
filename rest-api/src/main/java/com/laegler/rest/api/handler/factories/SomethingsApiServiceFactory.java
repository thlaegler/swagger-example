package com.laegler.rest.api.handler.factories;

import com.laegler.rest.api.handler.SomethingsApiService;
import com.laegler.rest.api.handler.impl.SomethingsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-09-30T18:02:13.696+02:00")
public class SomethingsApiServiceFactory {

   private final static SomethingsApiService service = new SomethingsApiServiceImpl();

   public static SomethingsApiService getSomethingsApi()
   {
      return service;
   }
}
