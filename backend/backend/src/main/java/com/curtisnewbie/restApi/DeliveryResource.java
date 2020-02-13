package com.curtisnewbie.restApi;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
import com.curtisnewbie.util.DeliveryOptionRepository;

@Path("delivery/option")
@RequestScoped
public class DeliveryResource {

    @EJB
    private DeliveryOptionRepository delivOptRepo;

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDelivOpt() {
        return Response.ok(delivOptRepo.getAllDelivOpt()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDelivOptById(@QueryParam("id") int id) {
        var opt = delivOptRepo.getDelivOptById(id);
        if (opt != null)
            return Response.ok(opt).build();
        else
            return Response.noContent().build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDelivOpt(DeliveryOption opt) {
        opt = delivOptRepo.createDelivOpt(opt);
        return Response.created(
                UriBuilder.fromPath("http://localhost:8080/api/delivery/option").queryParam("id", opt.getId()).build())
                .build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDelivOpt(DeliveryOption opt) {
        opt = delivOptRepo.updateDelivOpt(opt);
        return Response.ok(opt).build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDelivOptById(@QueryParam("id") int id) {
        boolean bool = delivOptRepo.removeDelivOptById(id);
        if (bool == true)
            return Response.ok("Delivery Option id: " + id + " deleted.").build();
        else
            return Response.noContent().build();
    }
}