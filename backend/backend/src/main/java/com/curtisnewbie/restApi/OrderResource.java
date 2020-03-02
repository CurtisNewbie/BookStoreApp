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
// import javax.ws.rs.core.UriBuilder;

import com.curtisnewbie.model.Order;
import com.curtisnewbie.security.SecurityRole;
import com.curtisnewbie.dao.OrderRepository;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/order")
@RequestScoped
public class OrderResource {

    @Inject
    OrderRepository orderRepo;

    /*
     * ------------------------------------------------------------
     * 
     * Creating order is not protected by authorisation and authentication mechanism
     * for demo purpose only. There may well be the process where the order is
     * verified as paid, etc.
     * 
     * ------------------------------------------------------------
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create an order", description = "Order's date is always overwritten in backend. An order must have at least one book,which's amount is greater than 0, and it must have selected a delivery option. (Note that this POST request is supposed to be protected by authorisation mechanism. Not using authentication and authorisation mechanism is only for demo purpose.)")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Order created and returned, this is intended so that the user can review the order that has been created.", content = @Content(schema = @Schema(implementation = Order.class))),
            @APIResponse(responseCode = "400", description = "Order cannot be created due to its invalidity, a message is returned describing the reason of failure.") })
    public Response createOrder(Order order) throws Exception {
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
        return Response.ok(orderRepo.getOrderById(order.getOrderId())).build();
    }

    @GET
    @RolesAllowed(SecurityRole.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get an order by its id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Order.class))),
            @APIResponse(responseCode = "404", description = "Order not found") })
    public Response getOrderById(@QueryParam("id") long orderId) {
        var order = orderRepo.getOrderById(orderId);
        if (order != null)
            return Response.ok(order).build();
        else
            return Response.noContent().build();
    }

    @GET
    @Path("/all")
    @RolesAllowed(SecurityRole.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all orders in an array")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = Order.class)))
    public Response getAllOrders() {
        var list = orderRepo.getAllOrders();
        return Response.ok(list).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(SecurityRole.ADMIN)
    @Operation(summary = "Delete an order by its id")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Order deleted"),
            @APIResponse(responseCode = "404", description = "Order is not found") })
    public Response deleteOrderById(@QueryParam("id") long id) {
        orderRepo.deleteOrderById(id);
        return Response.ok(String.format("Order: %d is deleted.", id)).build();
    }

    @PUT
    @RolesAllowed(SecurityRole.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update an order", description = "An order must have at least one book, which's amount is greater than 0, and it must have selected a delivery option.")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Order updated and returned"),
            @APIResponse(responseCode = "400", description = "Order is not updated due to its invalidity, a message is returned describing the reason of failure."),
            @APIResponse(responseCode = "404", description = "Order is not found") })
    public Response updateOrder(Order order) {
        order = orderRepo.updateOrder(order);
        return Response.ok(order).build();
    }
}