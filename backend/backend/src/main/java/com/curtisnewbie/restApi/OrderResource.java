package com.curtisnewbie.restApi;

import java.time.LocalDate;
import java.util.ArrayList;

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

import com.curtisnewbie.model.Order;
import com.curtisnewbie.util.OrderRepository;

@Path("order")
public class OrderResource {

    @EJB
    private OrderRepository orderRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        // has books in order
        if (order.getBooksOnOrder().size() > 0) {
            // overwrite date
            order.setDate(LocalDate.now());
            // set up JPA relationship
            var booksOnOrder = order.getBooksOnOrder();
            for (var b : booksOnOrder) {
                b.setOrder(order);
            }
            order = orderRepo.createOrder(order);
            return Response.created(
                    UriBuilder.fromPath("http://localhost:8080/api/order").queryParam("id", order.getOrderId()).build())
                    .build();
        } else {
            return Response.noContent().build();
        }
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