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
import com.curtisnewbie.util.HomeNewRepository;
import com.curtisnewbie.security.*;

@Path("new")
@RequestScoped
public class HomeNewResource {

    @Inject
    private HomeNewRepository homeNewRepo;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHomeNew() {
        var list = homeNewRepo.getHomeNews();
        return Response.ok(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHomeNewById(@QueryParam("id") long id) {
        var n = homeNewRepo.getHomeNewById(id);
        if (n != null)
            return Response.ok(n).build();
        else
            return Response.noContent().build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHomeNew(@NotNull HomeNew homeNew) {
        if (homeNewRepo.updateHomeNew(homeNew))
            return Response.ok(homeNew).build();
        else
            return Response.noContent().build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHomeNewById(@QueryParam("id") long id) {
        if (homeNewRepo.removeHomeNewById(id))
            return Response.ok("HomeNew id: " + id + " deleted.").build();
        else
            return Response.noContent().build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHomeNew(@NotNull HomeNew homeNew) {
        homeNew = homeNewRepo.createHomeNew(homeNew);
        var id = homeNew.getId();
        return Response.created(UriBuilder.fromPath("http://localhost:8080/api/new").queryParam("id", id).build())
                .build();
    }
}