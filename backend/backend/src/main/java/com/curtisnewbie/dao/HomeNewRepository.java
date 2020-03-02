package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;

import com.curtisnewbie.model.HomeNew;

@ApplicationScoped
public class HomeNewRepository {

    @Inject
    EntityManager em;

    /**
     * Persist a new HomeNew, the primary key of the HomeNew is set to null before
     * persistence.
     * 
     * @param homeNew HomeNew to be persisted
     * @return persisted HomeNew
     */
    @Transactional(value = TxType.REQUIRED)
    public HomeNew createHomeNew(@NotNull HomeNew homeNew) {
        homeNew.setId(null);
        em.persist(homeNew);
        return homeNew;
    }

    /**
     * Get all HomeNew(s)
     */
    @Transactional(value = TxType.SUPPORTS)
    public List<HomeNew> getHomeNews() {
        TypedQuery<HomeNew> query = em.createQuery("SELECT h FROM HomeNew h", HomeNew.class);
        return query.getResultList();
    }

    /**
     * Get HomeNew by id
     * 
     * @param id
     * @throws NotFoundException if HomeNew is not found
     */
    @Transactional(value = TxType.SUPPORTS)
    public HomeNew getHomeNewById(@NotNull Long id) {
        var homeNew = em.find(HomeNew.class, id);
        if (id != null && id >= 0 && homeNew != null)
            return homeNew;
        else
            throw new NotFoundException();
    }

    /**
     * Remove HomeNew by id
     * 
     * @param id
     * @throws NotFoundException if HomeNew is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public void removeHomeNewById(@NotNull Long id) {
        var homeNew = em.find(HomeNew.class, id);
        if (id != null && id >= 0 && homeNew != null)
            em.remove(homeNew);
        else
            throw new NotFoundException();
    }

    /**
     * Update HomeNew
     * 
     * @param homeNew HomeNew to be merged
     * @return updated HomeNew
     * @throws NotFoundException if the HomeNew is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public HomeNew updateHomeNew(@NotNull HomeNew homeNew) {
        var id = homeNew.getId();
        if (id != null && id >= 0 && em.find(HomeNew.class, homeNew.getId()) != null)
            return em.merge(homeNew);
        else
            throw new NotFoundException();
    }
}