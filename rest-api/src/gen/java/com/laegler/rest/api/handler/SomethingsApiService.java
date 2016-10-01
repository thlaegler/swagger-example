package com.laegler.rest.api.handler;

import com.laegler.rest.api.handler.*;
import com.laegler.rest.api.model.*;


import com.laegler.rest.api.model.Error;
import com.laegler.rest.api.model.Something;

import java.util.List;
import com.laegler.rest.api.handler.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-01T16:15:31.634+02:00")
public abstract class SomethingsApiService {
      public abstract Response add(Something body,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response findAll(SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response findById(Long id,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response remove(Something body,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response update(Something body,SecurityContext securityContext)
      throws NotFoundException;
}
