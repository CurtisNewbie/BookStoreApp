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

import com.curtisnewbie.model.HomeNew;

@Stateless
public class HomeNewRepository {

    @PersistenceContext(unitName = "bookStoreDB")
    private EntityManager em;

    @Resource
    private SessionContext sessionCtx;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public HomeNew createHomeNew(@NotNull HomeNew homeNew) {
        try {
            em.persist(homeNew);
            return homeNew;
        } catch (Exception e) {
            sessionCtx.setRollbackOnly();
            throw e;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<HomeNew> getHomeNews() {
        TypedQuery<HomeNew> query = em.createQuery("SELECT h FROM HomeNew h", HomeNew.class);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public HomeNew getHomeNewById(@NotNull long id) {
        return em.find(HomeNew.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeHomeNewById(@NotNull long id) {
        var homeNew = em.find(HomeNew.class, id);
        if (homeNew == null) {
            return false;
        } else {
            em.remove(homeNew);
            return true;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updateHomeNew(@NotNull HomeNew homeNew) {
        try {
            em.merge(homeNew);
            return true;
        } catch (Exception e) {
            sessionCtx.setRollbackOnly();
            throw e;
        }
    }
}