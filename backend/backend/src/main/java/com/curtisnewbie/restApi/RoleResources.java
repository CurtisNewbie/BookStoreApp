package com.curtisnewbie.restApi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;

/** Resources class used to check what the current role is */
@Path("role")
@RequestScoped
public class RoleResources {

    @Inject
    private JsonWebToken callerPrincipal;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response role() {
        return Response.ok("Roles: " + callerPrincipal.getGroups()).build();
    }

}