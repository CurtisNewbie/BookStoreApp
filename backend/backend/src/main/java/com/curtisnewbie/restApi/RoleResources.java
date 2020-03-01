package com.curtisnewbie.restApi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

/** Resources class used to check what the current role is */
@Path("/role")
@RequestScoped
public class RoleResources {

    @Inject
    private JsonWebToken callerPrincipal;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Return the role (or groups) of client based on the given JWT")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Return role or groups of this client", content = @Content(mediaType = "text/plain")),
            @APIResponse(responseCode = "401", description = "JWT is invalid (e.g., signed by different private key)") })
    public Response role() {
        return Response.ok("Roles: " + callerPrincipal.getGroups()).build();
    }

}