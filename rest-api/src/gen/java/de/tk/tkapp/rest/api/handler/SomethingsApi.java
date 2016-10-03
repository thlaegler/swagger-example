package de.tk.tkapp.rest.api.handler;

import de.tk.tkapp.rest.api.model.*;
import de.tk.tkapp.rest.api.handler.SomethingsApiService;
import de.tk.tkapp.rest.api.handler.factories.SomethingsApiServiceFactory;

import de.tk.tkapp.rest.api.model.Error;
import de.tk.tkapp.rest.api.model.Something;

import java.util.List;
import de.tk.tkapp.rest.api.handler.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/somethings")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-03T20:17:21.129+02:00")
public class SomethingsApi  {
   private final SomethingsApiService delegate = SomethingsApiServiceFactory.getSomethingsApi();

    @POST
    
    @Consumes({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    @Produces({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    public Response add( Something body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.add(body,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    public Response findAll(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findAll(securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    @Produces({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    public Response findById( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findById(id,securityContext);
    }
    @DELETE
    
    @Consumes({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    
    public Response remove( Something body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.remove(body,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    @Produces({ "application/json", "application/vnd.de.tk.tkapp.v1+json", "application/xml" })
    public Response update( Something body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.update(body,securityContext);
    }
}
