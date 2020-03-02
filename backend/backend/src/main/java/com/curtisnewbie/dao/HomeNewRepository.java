package com.curtisnewbie.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

    /**
     * Update HomeNew
     * 
     * @param homeNew HomeNew to be merged
     * @return updated HomeNew
     * @throws EntityNotFoundException if the HomeNew is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public HomeNew updateHomeNew(@NotNull HomeNew homeNew) {
        var id = homeNew.getId();
        if (id != null && id >= 0 && em.find(HomeNew.class, homeNew.getId()) != null)
            return em.merge(homeNew);
        else
            throw new EntityNotFoundException();
    }
}