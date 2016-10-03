package de.tk.tkapp.rest.api.handler.impl;

import de.tk.tkapp.rest.api.handler.*;
import de.tk.tkapp.rest.api.model.*;
import java.util.ArrayList;
import javax.ws.rs.core.GenericEntity;


import de.tk.tkapp.rest.api.model.Error;
import de.tk.tkapp.rest.api.model.Something;

import java.util.List;
import de.tk.tkapp.rest.api.handler.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-03T19:59:10.670+02:00")
public class SomethingsApiServiceImpl extends SomethingsApiService {
      @Override
      public Response add(Something body,SecurityContext securityContext)
      throws NotFoundException {
      
      // TODO: This is REST API mock-method returning an example response.
      // You have to customize the result in this method like:
      // Something result = builder.id(1).name("my name").build();
      Something result = Something.getExample();
      return Response.ok().entity(result).build();
      
      
  }
      @Override
      public Response findAll(SecurityContext securityContext)
      throws NotFoundException {
      
      // TODO: This is REST API mock-method returning an example response.
      // You have to customize the result in this method like:
      // Something result = builder.id(1).name("my name").build();
      List<Something> results = new ArrayList<Something>();
      results.add(Something.getExample());
      results.add(Something.getExample());
      results.add(Something.getExample());
      return Response.ok().entity(new GenericEntity<List<Something>>(results){}).build();
      
      
  }
      @Override
      public Response findById(Long id,SecurityContext securityContext)
      throws NotFoundException {
      
      // TODO: This is REST API mock-method returning an example response.
      // You have to customize the result in this method like:
      // Something result = builder.id(1).name("my name").build();
      Something result = Something.getExample();
      return Response.ok().entity(result).build();
      
      
  }
      @Override
      public Response remove(Something body,SecurityContext securityContext)
      throws NotFoundException {
      
      // TODO: This is REST API mock-method returning an example response.
      // You have to customize the result in this method like:
      // Something result = builder.id(1).name("my name").build();
      ApiResponseMessage result = new ApiResponseMessage(ApiResponseMessage.OK, "dummy response");
      return Response.ok().entity(result).build();
      
      
  }
      @Override
      public Response update(Something body,SecurityContext securityContext)
      throws NotFoundException {
      
      // TODO: This is REST API mock-method returning an example response.
      // You have to customize the result in this method like:
      // Something result = builder.id(1).name("my name").build();
      Something result = Something.getExample();
      return Response.ok().entity(result).build();
      
      
  }
}
