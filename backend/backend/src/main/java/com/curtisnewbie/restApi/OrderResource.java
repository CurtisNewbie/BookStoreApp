package com.curtisnewbie.restApi;

import java.time.LocalDate;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
// import javax.ws.rs.core.UriBuilder;

import com.curtisnewbie.model.Order;
import com.curtisnewbie.security.SecurityRole;
import com.curtisnewbie.util.OrderRepository;

@Path("order")
@RequestScoped
public class OrderResource {

    @EJB
    private OrderRepository orderRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        // has books and delivery option selected in order
        if (order.getBooksOnOrder().size() > 0 && order.getDeliveryOption() != null) {
            // overwrite date
            order.setDate(LocalDate.now());
            // set up JPA relationship
            var booksOnOrder = order.getBooksOnOrder();
            for (var b : booksOnOrder) {
                b.setOrder(order);
            }
            // persist order
            order = orderRepo.createOrder(order);
            /*
             * ---------------------------------------------
             * 
             * May be only the admin should be able to get the order by id, thus returning
             * the URI path doesn't really make sense. The client will be the one to create
             * order, say after they successfully make the payment. They then should only be
             * able to review the order they created or belong to them. Thus returning the
             * created order in a JSON format which is then displayed on their browser, may
             * be a better approach.
             * 
             * ---------------------------------------------
             */
            // return Response.created(
            // UriBuilder.fromPath("http://localhost:8080/api/order").queryParam("id",
            // order.getOrderId()).build())
            // .build();
            return Response.ok(order).build();
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
    @RolesAllowed(SecurityRole.ADMIN)
    public Response getAllOrders() {
        var list = orderRepo.getAllOrders();
        return Response.ok(list).build();
    }

    @DELETE
    @RolesAllowed(SecurityRole.ADMIN)
    public Response deleteOrderById(@QueryParam("id") long id) {
        if (orderRepo.deleteOrderById(id))
            return Response.ok().build();
        else
            return Response.noContent().build();
    }
}