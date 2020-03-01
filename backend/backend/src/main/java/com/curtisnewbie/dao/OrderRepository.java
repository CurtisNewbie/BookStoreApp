package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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

    /**
     * Persist a new Order in database.
     * 
     * @param order Order to be persisted
     * @return the persisted Order
     * @throws DuplicatePrimaryKeyException if an Order with same primary key exists
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
        } catch (PersistenceException e) {
            throw new DuplicatePrimaryKeyException();
        }
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

    /**
     * update order
     * 
     * @param order Order to be persisted
     * @return the updated order
     * @throws EntityNotFoundException if Order is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public Order updateOrder(@NotNull Order order) {
        if (em.find(Order.class, order.getOrderId()) != null)
            return em.merge(order);
        else
            throw new EntityNotFoundException();
    }
}