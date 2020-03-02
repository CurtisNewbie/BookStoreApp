package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.NotFoundException;

import com.curtisnewbie.model.*;

/** DAO or class that manage db interactions for DeliveryOption resources */
@ApplicationScoped
public class DeliveryOptionRepository {

    @Inject
    EntityManager em;

    /**
     * Get all DeliveryOption
     * 
     * @return all DeliveryOption(s)
     */
    @Transactional(value = TxType.SUPPORTS)
    public List<DeliveryOption> getAllDelivOpt() {
        TypedQuery<DeliveryOption> query = em.createQuery("SELECT d FROM DeliveryOption d", DeliveryOption.class);
        return query.getResultList();
    }

    /**
     * Get DeliveryOption by id
     * 
     * @param id
     * @return
     * @throws NotFoundException when the DeliveryOption is not found
     */
    @Transactional(value = TxType.SUPPORTS)
    public DeliveryOption getDelivOptById(int id) {
        var opt = em.find(DeliveryOption.class, id);
        if (opt != null)
            return opt;
        else
            throw new NotFoundException();
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
     * @throws NotFoundException if the DeliveryOption is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public DeliveryOption updateDelivOpt(DeliveryOption opt) {
        var id = opt.getId();
        if (id != null && id >= 0 && em.find(DeliveryOption.class, id) != null)
            return em.merge(opt);
        else
            throw new NotFoundException();
    }

    /**
     * Delete DeliveryOption by its id
     * 
     * @param id
     * @throws NotFoundException if DeliveryOption is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public void removeDelivOptById(int id) {
        DeliveryOption opt = em.find(DeliveryOption.class, id);
        if (opt != null)
            em.remove(opt);
        else
            throw new NotFoundException();
    }
}