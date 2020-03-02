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

    /**
     * Persist a new DeliveryOption, the primary key of the DeliveryOption is set to
     * null before persistence.
     * 
     * @param opt DeliveryOption to be persisted
     * @return persisted DeliveryOption
     */
    @Transactional(value = TxType.REQUIRED)
    public DeliveryOption createDelivOpt(DeliveryOption opt) {
        opt.setId(null);
        em.persist(opt);
        return opt;
    }

    /**
     * Update Delivery Option
     * 
     * @param opt DeliveryOption to be merged
     * @return updated DeliveryOption
     * @throws EntityNotFoundException if the DeliveryOption is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public DeliveryOption updateDelivOpt(DeliveryOption opt) {
        if (em.find(DeliveryOption.class, opt.getId()) != null)
            return em.merge(opt);
        else
            throw new EntityNotFoundException();
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