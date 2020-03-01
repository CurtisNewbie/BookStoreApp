package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.model.DeliveryOption;
import com.curtisnewbie.model.Order;

// Class that manages interaction with database for Order resources
@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager em;

    @Inject
    TransactionManager tm;

    /*
     * 
     * -------------------------------------
     * 
     * Not implemented:
     * 
     * Handle Exceptions in future commits
     * 
     * -------------------------------------
     */
    /**
     * Persist Order in database.
     * 
     * @param order
     */
    @Transactional(value = TxType.REQUIRED)
    public Order createOrder(@NotNull Order order) throws Exception {
        try {
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
        } catch (Exception e) {
            tm.setRollbackOnly();
        }
        return null;
    }

    @Transactional(value = TxType.SUPPORTS)
    public Order getOrderById(@NotNull long id) {
        Order ord = em.find(Order.class, id);
        return ord;
    }

    @Transactional(value = TxType.SUPPORTS)
    public List<Order> getAllOrders() {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
        return query.getResultList();
    }

    /**
     * delete order by id
     * 
     * @param id
     * @return {@code FALSE} if failed, else {@code TRUE}
     */
    @Transactional(value = TxType.REQUIRED)
    public boolean deleteOrderById(@NotNull long id) {
        var o = em.find(Order.class, id);
        if (o != null) {
            em.remove(o);
            return true;
        }
        return false;
    }

    /*
     * 
     * -------------------------------------
     * 
     * Not implemented:
     * 
     * Handle Exceptions in future commits
     * 
     * -------------------------------------
     */
    /**
     * update order
     * 
     * @param order
     * @return the updated order
     */
    public Order updateOrder(@NotNull Order order) {
        em.merge(order);
        return order;
    }
}