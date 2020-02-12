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

import com.curtisnewbie.model.*;

/** DAO or class that manage db interactions for DeliveryOption resources */
@Stateless
public class DeliveryOptionRepository {

    @PersistenceContext(unitName = "bookStoreDB")
    private EntityManager em;

    @Resource
    private SessionContext sessionCtx;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<DeliveryOption> getAllDelivOpt() {
        TypedQuery<DeliveryOption> query = em.createQuery("SELECT d FROM DeliveryOption d", DeliveryOption.class);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public DeliveryOption getDelivOptById(int id) {
        return em.find(DeliveryOption.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DeliveryOption createDelivOpt(DeliveryOption opt) {
        try {
            em.persist(opt);
            return opt;
        } catch (Exception e) {
            sessionCtx.setRollbackOnly();
            throw e;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DeliveryOption updateDelivOpt(DeliveryOption opt) {
        try {
            em.merge(opt);
            return opt;
        } catch (Exception e) {
            sessionCtx.setRollbackOnly();
            throw e;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeDelivOptById(int id) {
        DeliveryOption opt = em.find(DeliveryOption.class, id);
        if (opt != null) {
            em.remove(opt);
            return true;
        } else
            return false;
    }
}