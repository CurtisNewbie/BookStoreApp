package com.curtisnewbie.util;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.curtisnewbie.model.Order;

// Class that manages interaction with database for Order resources
@Stateless
public class OrderRepository {

    @PersistenceContext(unitName = "bookStoreDB")
    private EntityManager em;

    @Resource
    private SessionContext sessionCtx;

    /**
     * Persist Order in database.
     * 
     * @param order
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createOrder(@NotNull Order order) {
        try {
            em.persist(order);
        } catch (Exception e) {
            sessionCtx.setRollbackOnly();
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Order getOrderById(@NotNull long id) {
        Order ord = em.find(Order.class, id);
        return ord;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteOrderById(@NotNull long id) {
        var o = em.find(Order.class, id);
        if (o != null) {
            em.remove(o);
            return true;
        }
        return false;
    }
}