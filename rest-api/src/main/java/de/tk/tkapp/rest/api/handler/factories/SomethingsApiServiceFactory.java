package de.tk.tkapp.rest.api.handler.factories;

import de.tk.tkapp.rest.api.handler.SomethingsApiService;
import de.tk.tkapp.rest.api.handler.impl.SomethingsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-03T19:59:10.670+02:00")
public class SomethingsApiServiceFactory {

   private final static SomethingsApiService service = new SomethingsApiServiceImpl();

   public static SomethingsApiService getSomethingsApi()
   {
      return service;
   }
}
