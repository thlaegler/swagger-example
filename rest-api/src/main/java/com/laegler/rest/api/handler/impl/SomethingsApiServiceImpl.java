package com.laegler.rest.api.handler.impl;

import com.laegler.rest.api.handler.*;
import com.laegler.rest.api.model.*;
import java.util.ArrayList;


import com.laegler.rest.api.model.Error;
import com.laegler.rest.api.model.Something;

import java.util.List;
import com.laegler.rest.api.handler.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-01T14:17:47.852+02:00")
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
      ArrayList<Something> result = new ArrayList<Something>();
      result.add(Something.getExample());
      result.add(Something.getExample());
      
      return Response.ok().entity(result).build();
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
      ApiResponseMessage result = new ApiResponseMessage(ApiResponseMessage.OK, "response example");
      
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
