package com.curtisnewbie.dao;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.model.DeliveryOption;
import com.curtisnewbie.model.Order;

// Class that manages interaction with database for Order resources
@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager em;

    /**
     * Persist a new Order in database, the primary key of the Order is set to null
     * before persistence.
     * 
     * @param order Order to be persisted
     * @return the persisted Order
     * @throws NoBookInOrderException           if there is no Book in this Order
     * @throws NoDeliveryOptionInOrderException if there is no DeliveryOption in
     *                                          this Order
     */
    @Transactional(value = TxType.REQUIRED)
    public Order createOrder(@NotNull Order order) {
        // validate books in Order and DeliveryOption
        validateOrder(order);
        // setup JPA relationship
        setBookOnOrderRelation(order);
        // overwrite date
        order.setDate(LocalDate.now());
        // set primary key to null
        order.setOrderId(null);
        // calculate price before persistence
        double sum = 0;
        var list = order.getBooksOnOrder();
        for (var b : list) {
            sum += (em.find(Book.class, b.getBook().getId()).getPrice() * b.getAmount());
        }
        sum += (em.find(DeliveryOption.class, order.getDeliveryOption().getId()).getPrice());
        order.setPrice(sum);
        em.persist(order);
        return order;
    }

    /**
     * Get Order by its id
     * 
     * @param id
     * @return the Order that is found
     * @throws NotFoundException if the Order is not found
     */
    @Transactional(value = TxType.SUPPORTS)
    public Order getOrderById(@NotNull Long id) {
        Order ord = em.find(Order.class, id);
        if (id != null && id >= 0 && ord != null)
            return ord;
        else
            throw new NotFoundException();
    }

    /**
     * Get all Orders
     * 
     * @return all Orders
     */
    @Transactional(value = TxType.SUPPORTS)
    public List<Order> getAllOrders() {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
        return query.getResultList();
    }

    /**
     * delete Order by id
     * 
     * @param id
     * @throws NotFoundException if the Order is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public void deleteOrderById(@NotNull long id) {
        var o = em.find(Order.class, id);
        if (o != null)
            em.remove(o);
        else
            throw new NotFoundException();
    }

    /**
     * update order
     * 
     * @param order Order to be persisted
     * @return the updated order
     * @throws NotFoundException                if Order is not found
     * @throws NoBookInOrderException           if there is no Book in this Order
     * @throws NoDeliveryOptionInOrderException if there is no DeliveryOption in
     *                                          this Order
     */
    @Transactional(value = TxType.REQUIRED)
    public Order updateOrder(@NotNull Order order) {
        var id = order.getOrderId();
        if (id != null && id >= 0 && em.find(Order.class, order.getOrderId()) != null) {
            validateOrder(order);
            setBookOnOrderRelation(order);
            return em.merge(order);
        } else {
            throw new NotFoundException();
        }
    }

    /**
     * Check the Books in Order and DeliveryOption
     * 
     * @throws NoBookInOrderException           if there is no Book in this Order
     * @throws NoDeliveryOptionInOrderException if there is no DeliveryOption in
     *                                          this Order
     * @param order
     */
    private void validateOrder(Order order) {
        if (order.getBooksOnOrder().size() <= 0) {
            throw new NoBookInOrderException();
        }
        if (order.getDeliveryOption() == null) {
            throw new NoDeliveryOptionInOrderException();
        }
    }

    /**
     * Set up the JPA relationship in BooksOnOrder
     * 
     * @param order
     */
    private void setBookOnOrderRelation(Order order) {
        var booksOnOrder = order.getBooksOnOrder();
        for (var b : booksOnOrder) {
            b.setOrder(order);
        }
    }
}