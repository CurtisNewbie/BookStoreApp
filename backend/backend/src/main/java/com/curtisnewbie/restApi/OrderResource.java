package com.curtisnewbie.restApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.curtisnewbie.dto.OrderDTO;
import com.curtisnewbie.model.Address;
import com.curtisnewbie.model.Book;
import com.curtisnewbie.model.Order;
import com.curtisnewbie.util.OrderRepository;

@Path("order")
public class OrderResource {

    @EJB
    private OrderRepository orderRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO);
        // has books in order
        if (order.getBooksOnOrder().size() > 0) {
            // overwrite date
            order.setDate(new Date());
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
            return Response.ok(new OrderDTO(order)).build();
        else
            return Response.noContent().build();
    }

    /*
     * ---------------------------------------
     *
     * dummy data:
     * 
     * Should be destroyed after front end is implemented
     * 
     * ---------------------------------------
     * 
     */
    @GET
    @Path("dummy")
    public void getDummyOrder() {
        Order o = new Order();
        o.setFirstName("Curtis");
        o.setLastName("Newbie");
        o.setDate(new Date());
        Address add = new Address();
        add.setCity("Bourne");
        add.setCounty("county");
        add.setFirstLine("3rd st");
        o.setAddress(add);
        Book book = new Book();
        book.setId("123-456");
        o.setBooksOnOrder(new ArrayList<Book>());
        o.getBooksOnOrder().add(book);
        orderRepo.createOrder(o);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllOrders() {
        var list = orderRepo.getAllOrders();
        List<OrderDTO> dtoList = new ArrayList<>();
        for (Order o : list) {
            dtoList.add(new OrderDTO(o));
        }
        return Response.ok(dtoList).build();
    }

    @DELETE
    public Response deleteOrderById(@QueryParam("id") long id) {
        if (orderRepo.deleteOrderById(id))
            return Response.ok().build();
        else
            return Response.noContent().build();
    }
}