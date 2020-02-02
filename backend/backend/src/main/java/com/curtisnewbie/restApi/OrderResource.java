package com.curtisnewbie.restApi;

import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.curtisnewbie.model.Address;
import com.curtisnewbie.model.Order;
import com.curtisnewbie.util.OrderRepository;

@Path("order")
public class OrderResource {

    @EJB
    OrderRepository orderRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        // overwrite date
        order.setDate(new Date());
        orderRepo.createOrder(order);
        return Response.created(
                UriBuilder.fromPath("http://localhost:8080/api/order").queryParam("id", order.getOrderId()).build())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@QueryParam("id") long orderId) {
        var order = orderRepo.getOrderById(orderId);
        if (order != null)
            return Response.ok(order).build();
        else
            return Response.noContent().build();
    }

    /*
     * ---------------------------------------
     *
     * Should be destroyed after front end is implemented
     * 
     * ---------------------------------------
     * 
     */
    @GET
    @Path("dummy")
    public void getDummyOrder() {
        Order order = new Order();
        order.setFirstName("Curtis");
        order.setLastName("Newbie");
        order.setDate(new Date());
        Address add = new Address();
        add.setCity("Bourne");
        add.setCounty("county");
        add.setFirstLine("3rd st");
        order.setAddress(add);
        orderRepo.createOrder(order);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllOrders() {
        var list = orderRepo.getAllOrders();
        return Response.ok(list).build();
    }

    @DELETE
    public Response deleteOrderById(@QueryParam("id") long id) {
        if (orderRepo.deleteOrderById(id))
            return Response.ok().build();
        else
            return Response.noContent().build();
    }
}