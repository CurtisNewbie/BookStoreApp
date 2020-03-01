package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.curtisnewbie.model.*;

/** DAO or class that manage db interactions for DeliveryOption resources */
@ApplicationScoped
public class DeliveryOptionRepository {

    @Inject
    EntityManager em;

    @Transactional(value = TxType.SUPPORTS)
    public List<DeliveryOption> getAllDelivOpt() {
        TypedQuery<DeliveryOption> query = em.createQuery("SELECT d FROM DeliveryOption d", DeliveryOption.class);
        return query.getResultList();
    }

    @Transactional(value = TxType.SUPPORTS)
    public DeliveryOption getDelivOptById(int id) {
        return em.find(DeliveryOption.class, id);
    }

    /*
     * -------------------------------------
     * 
     * Not implemented:
     * 
     * Handle Exceptions in future commits
     * 
     * -------------------------------------
     */
    @Transactional(value = TxType.REQUIRED)
    public DeliveryOption createDelivOpt(DeliveryOption opt) {
        em.persist(opt);
        return opt;
    }

    /*
     * -------------------------------------
     * 
     * Not implemented:
     * 
     * Handle Exceptions in future commits
     * 
     * -------------------------------------
     */
    @Transactional(value = TxType.REQUIRED)
    public DeliveryOption updateDelivOpt(DeliveryOption opt) {
        em.merge(opt);
        return opt;
    }

    @Transactional(value = TxType.REQUIRED)
    public boolean removeDelivOptById(int id) {
        DeliveryOption opt = em.find(DeliveryOption.class, id);
        if (opt != null) {
            em.remove(opt);
            return true;
        } else
            return false;
    }
}