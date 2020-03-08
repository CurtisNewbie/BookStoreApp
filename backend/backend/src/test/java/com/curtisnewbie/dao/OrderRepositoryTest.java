package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.curtisnewbie.model.Address;
import com.curtisnewbie.model.Book;
import com.curtisnewbie.model.BookOrder;
import com.curtisnewbie.model.DeliveryOption;
import com.curtisnewbie.model.Order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class OrderRepositoryTest {

    @Inject
    OrderRepository orderRepo;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void shouldThrowExceptions() {
        final var wrongId = 123456654l;
        /*
         * For situations where id doesn't exist
         */
        assertThrows(WebApplicationException.class, () -> {
            orderRepo.getOrderById(wrongId);
        });
        assertThrows(WebApplicationException.class, () -> {
            orderRepo.deleteOrderById(wrongId);
        });
        assertThrows(WebApplicationException.class, () -> {
            orderRepo.updateOrder(createValidOrder(wrongId));
        });

        /*
         * For situations where the order is invalid
         */
        assertThrows(WebApplicationException.class, () -> {
            // create - bookOnOrder should not be empty list
            Order order = createValidOrder(null);
            order.setBooksOnOrder(new ArrayList<>());
            orderRepo.createOrder(order);
        });
        assertThrows(WebApplicationException.class, () -> {
            // update - bookOnOrder should not be empty list
            Order order = createValidOrder(null);
            order.setBooksOnOrder(new ArrayList<>());
            orderRepo.updateOrder(order);
        });
        assertThrows(WebApplicationException.class, () -> {
            // create - bookOnOrder should have at least one book
            Order order = createValidOrder(null);
            BookOrder bookOrder = order.getBooksOnOrder().get(0);
            bookOrder.setAmount(0);
            orderRepo.createOrder(order);
        });
        assertThrows(WebApplicationException.class, () -> {
            // update - bookOnOrder should have at least one book
            Order order = createValidOrder(null);
            var bookOrder = order.getBooksOnOrder().get(0);
            bookOrder.setAmount(0);
            orderRepo.updateOrder(order);
        });
        assertThrows(WebApplicationException.class, () -> {
            // create - order must have deliveryOption
            Order order = createValidOrder(null);
            order.setDeliveryOption(null);
            orderRepo.createOrder(order);
        });
        assertThrows(WebApplicationException.class, () -> {
            // update - order must have deliveryOption
            Order order = createValidOrder(null);
            order.setDeliveryOption(null);
            orderRepo.updateOrder(order);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void shouldCreateOrder() {
        Order order = createValidOrder(null);
        var createdOrder = orderRepo.createOrder(order);
        assertNotNull(createdOrder);
        assertEquals(1, createdOrder.getBooksOnOrder().size());
        assertEquals(26.95, createdOrder.getPrice());
        assertNotNull(createdOrder.getDate());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void shouldUpdateOrder() {
        var allOrders = orderRepo.getAllOrders();
        assertTrue(allOrders.size() > 0);
        var order = allOrders.get(0);
        assertNotNull(order);

        // change firstname
        var diffFirstName = order.getFirstName() + "abc";
        order.setFirstName(diffFirstName);
        // change amount
        order.getBooksOnOrder().get(0).setAmount(100);
        // change delivery option
        var diffDelivOpt = new DeliveryOption();
        diffDelivOpt.setId(22);
        order.setDeliveryOption(diffDelivOpt);

        var updatedOrder = orderRepo.updateOrder(order);
        assertNotNull(updatedOrder);
        assertEquals(order.getOrderId(), updatedOrder.getOrderId());
        assertEquals(diffFirstName, updatedOrder.getFirstName());
        assertEquals(100, updatedOrder.getBooksOnOrder().get(0).getAmount());
        assertEquals(diffDelivOpt.getId(), updatedOrder.getDeliveryOption().getId());
        assertNotNull(updatedOrder.getDeliveryOption().getName());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void shouldDeleteOrder() {
        var allOrders = orderRepo.getAllOrders();
        assertTrue(allOrders.size() > 0);
        var order = allOrders.get(0);
        assertNotNull(order);

        var id = order.getOrderId();
        assertNotNull(id);
        orderRepo.deleteOrderById(id);
    }

    /**
     * Get Order that doesn't have id, booksOnOrder, and deliveryOption
     * 
     * @return temp order for testing
     */
    private Order createOrderTemplate() {
        Order order = new Order();
        order.setFirstName("firstName");
        order.setLastName("lastName");
        var address = new Address();
        address.setFirstLine("firstLine");
        address.setCity("city");
        address.setCounty("county");
        address.setPostCode("postCode");
        order.setAddress(address);
        return order;
    }

    /**
     * <p>
     * Get Order with the given id, valid deliveryOption, and valid booksOnOrder
     * </p>
     * <p>
     * Note that this method is written based on the import_test.sql script. Modify
     * the id for book and deliveryOption if necessary.
     * </p>
     * 
     * @param id the id of returned order. If this given id is not {@code NULL},
     *           it's set to the order returned, else the id of the returned order
     *           will still be {@code NULL}
     * @return valid order that can be persisted or merged
     */
    private Order createValidOrder(Long id) {
        Order order = createOrderTemplate();
        if (id != null)
            order.setOrderId(id);

        DeliveryOption opt = new DeliveryOption();
        opt.setId(11);

        Book book = new Book();
        book.setId(11l);

        // setup jpa relation
        order.setDeliveryOption(opt);
        order.setBooksOnOrder(new ArrayList<>());
        book.setOrders(new ArrayList<>());

        // valid data
        BookOrder bookOrder = new BookOrder();
        bookOrder.setBook(book);
        bookOrder.setOrder(order);
        bookOrder.setAmount(1);
        order.getBooksOnOrder().add(bookOrder);
        book.getOrders().add(bookOrder);

        // invalid data to test whether they are filtered out
        BookOrder bookOrderTwo = new BookOrder();
        bookOrderTwo.setBook(book);
        bookOrderTwo.setOrder(order);
        bookOrderTwo.setAmount(0);
        BookOrder bookOrderThree = new BookOrder();
        bookOrderThree.setBook(book);
        bookOrderThree.setOrder(order);
        bookOrderThree.setAmount(0);
        order.getBooksOnOrder().add(bookOrderTwo);
        order.getBooksOnOrder().add(bookOrderThree);
        book.getOrders().add(bookOrderTwo);
        book.getOrders().add(bookOrderThree);
        return order;
    }
}