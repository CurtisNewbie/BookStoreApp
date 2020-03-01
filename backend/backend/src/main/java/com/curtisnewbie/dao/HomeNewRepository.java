package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;

import com.curtisnewbie.model.HomeNew;

@ApplicationScoped
public class HomeNewRepository {

    @Inject
    EntityManager em;

    /**
     * Persist a new HomeNew
     * 
     * @param homeNew HomeNew to be persisted
     * @return persisted HomeNew
     * @throws DuplicatePrimaryKeyException if a HomeNew with same primary key
     *                                      exists
     */
    @Transactional(value = TxType.REQUIRED)
    public HomeNew createHomeNew(@NotNull HomeNew homeNew) {
        try {
            em.persist(homeNew);
            return homeNew;
        } catch (PersistenceException e) {
            throw new DuplicatePrimaryKeyException();
        }
    }

    @Transactional(value = TxType.SUPPORTS)
    public List<HomeNew> getHomeNews() {
        TypedQuery<HomeNew> query = em.createQuery("SELECT h FROM HomeNew h", HomeNew.class);
        return query.getResultList();
    }

    @Transactional(value = TxType.SUPPORTS)
    public HomeNew getHomeNewById(@NotNull long id) {
        return em.find(HomeNew.class, id);
    }

    @Transactional(value = TxType.REQUIRED)
    public boolean removeHomeNewById(@NotNull long id) {
        var homeNew = em.find(HomeNew.class, id);
        if (homeNew == null) {
            return false;
        } else {
            em.remove(homeNew);
            return true;
        }
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
    @Transactional(value = TxType.REQUIRED)
    public boolean updateHomeNew(@NotNull HomeNew homeNew) {
        em.merge(homeNew);
        return true;
    }
}