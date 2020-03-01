package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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

    /**
     * Persist a new DeliveryOption
     * 
     * @param opt DeliveryOption to be persisted
     * @return persisted DeliveryOption
     * @throws DuplicatePrimaryKeyException if a DeliveryOption with same primay key
     *                                      exists
     */
    @Transactional(value = TxType.REQUIRED)
    public DeliveryOption createDelivOpt(DeliveryOption opt) {
        try {
            em.persist(opt);
            return opt;
        } catch (PersistenceException e) {
            throw new DuplicatePrimaryKeyException();
        }
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