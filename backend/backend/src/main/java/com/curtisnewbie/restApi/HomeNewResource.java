package com.curtisnewbie.restApi;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.curtisnewbie.model.HomeNew;
import com.curtisnewbie.dao.HomeNewRepository;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.curtisnewbie.security.*;

@Path("/new")
@RequestScoped
public class HomeNewResource {

    @Inject
    HomeNewRepository homeNewRepo;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all home news in an array")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = HomeNew.class)))
    public Response getAllHomeNew() {
        var list = homeNewRepo.getHomeNews();
        return Response.ok(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a home new by its id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = HomeNew.class))),
            @APIResponse(responseCode = "404", description = "Home new is not found") })
    public Response getHomeNewById(@QueryParam("id") long id) {
        return Response.ok(homeNewRepo.getHomeNewById(id)).build();
    }

    @PUT
    @RolesAllowed(SecurityRole.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update an existing HomeNew")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "HomeNew updated and returned", content = @Content(schema = @Schema(implementation = HomeNew.class))),
            @APIResponse(responseCode = "404", description = "HomeNew is not found") })
    public Response updateHomeNew(@NotNull HomeNew homeNew) {
        return Response.ok(homeNewRepo.updateHomeNew(homeNew)).build();
    }

    @DELETE
    @RolesAllowed(SecurityRole.ADMIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Delete a home new by its id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Home new deleted, a message about this is returned", content = @Content(schema = @Schema(implementation = HomeNew.class))),
            @APIResponse(responseCode = "404", description = "HomeNew is not found") })
    public Response deleteHomeNewById(@QueryParam("id") long id) {
        homeNewRepo.removeHomeNewById(id);
        return Response.ok("HomeNew id: " + id + " deleted.").build();
    }

    @POST
    @RolesAllowed(SecurityRole.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a home new")
    @APIResponse(responseCode = "201", description = "Home new created, URI to it is returned in header \"location\"")
    public Response createHomeNew(@NotNull HomeNew homeNew) {
        homeNew = homeNewRepo.createHomeNew(homeNew);
        var id = homeNew.getId();
        return Response.created(UriBuilder.fromPath("http://localhost:8080/api/new").queryParam("id", id).build())
                .build();
    }
}