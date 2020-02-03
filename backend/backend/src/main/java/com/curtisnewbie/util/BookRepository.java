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

import com.curtisnewbie.model.Book;

// Class that manages interaction with database for Book resources
@Stateless
public class BookRepository {

    @PersistenceContext(unitName = "bookStoreDB")
    private EntityManager em;

    @Resource
    private SessionContext sessionCtx;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createBook(@NotNull Book book) {
        try {
            em.persist(book);
        } catch (Exception e) {
            sessionCtx.setRollbackOnly();
            throw e;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Book> getBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeBookById(String id) {
        boolean res;
        var b = em.find(Book.class, id);
        if (b != null) {
            em.remove(b);
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * Get Book By Id
     * 
     * @param id id of the book
     * @return {@code NULL} if not found, else the Book object that contains this
     *         id.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Book getBookById(String id) {
        return em.find(Book.class, id);
    }
}