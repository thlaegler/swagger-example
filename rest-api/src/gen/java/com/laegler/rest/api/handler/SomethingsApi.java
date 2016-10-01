package com.laegler.rest.api.handler;

import com.laegler.rest.api.model.*;
import com.laegler.rest.api.handler.SomethingsApiService;
import com.laegler.rest.api.handler.factories.SomethingsApiServiceFactory;

import com.laegler.rest.api.model.Error;
import com.laegler.rest.api.model.Something;

import java.util.List;
import com.laegler.rest.api.handler.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/somethings")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-01T16:15:31.634+02:00")
public class SomethingsApi  {
   private final SomethingsApiService delegate = SomethingsApiServiceFactory.getSomethingsApi();

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public Response add( Something body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.add(body,securityContext);
    }
    @GET
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public Response findAll(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findAll(securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public Response findById( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findById(id,securityContext);
    }
    @DELETE
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public Response remove( Something body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.remove(body,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public Response update( Something body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.update(body,securityContext);
    }
}
