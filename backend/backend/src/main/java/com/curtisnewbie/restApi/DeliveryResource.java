package com.curtisnewbie.restApi;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import com.curtisnewbie.model.DeliveryOption;
import com.curtisnewbie.security.SecurityRole;
import com.curtisnewbie.dao.DeliveryOptionRepository;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/delivery/option")
@RequestScoped
public class DeliveryResource {

    @Inject
    DeliveryOptionRepository delivOptRepo;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all DeliveryOption(s) in an array")
    @APIResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = DeliveryOption.class)))
    public Response getAllDelivOpt() {
        return Response.ok(delivOptRepo.getAllDelivOpt()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a DeliveryOption by its id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryOption.class))),
            @APIResponse(responseCode = "404", description = "DeliveryOption is not found") })
    public Response getDelivOptById(@QueryParam("id") int id) {
        return Response.ok(delivOptRepo.getDelivOptById(id)).build();
    }

    @POST
    @RolesAllowed(SecurityRole.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a DeliveryOption")
    @APIResponse(responseCode = "201", description = "DeliveryOption created, URI to it is returned in header \"location\"")
    public Response createDelivOpt(DeliveryOption opt) {
        opt = delivOptRepo.createDelivOpt(opt);
        return Response.created(
                UriBuilder.fromPath("http://localhost:8080/api/delivery/option").queryParam("id", opt.getId()).build())
                .build();
    }

    @PUT
    @RolesAllowed(SecurityRole.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update an existing DeliveryOption")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "DeliveryOption updated", content = @Content(schema = @Schema(implementation = DeliveryOption.class))),
            @APIResponse(responseCode = "404", description = "DeliveryOption is not found.") })
    public Response updateDelivOpt(DeliveryOption opt) {
        opt = delivOptRepo.updateDelivOpt(opt);
        return Response.ok(opt).build();
    }

    @DELETE
    @RolesAllowed(SecurityRole.ADMIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Delete a DeliveryOption by its id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "DeliveryOption deleted, a message about this is returned", content = @Content(schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "404", description = "DeliveryOption is not found") })
    public Response deleteDelivOptById(@QueryParam("id") int id) {
        delivOptRepo.removeDelivOptById(id);
        return Response.ok("Delivery Option id: " + id + " deleted.").build();
    }
}