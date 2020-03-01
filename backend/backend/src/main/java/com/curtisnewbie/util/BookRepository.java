package com.curtisnewbie.util;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;

import com.curtisnewbie.model.Book;

// Class that manages interaction with database for Book resources
@ApplicationScoped
public class BookRepository {

    @Inject
    EntityManager em;

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
    public Book createBook(@NotNull Book book) {
        em.persist(book);
        return book;
    }

    @Transactional(value = TxType.SUPPORTS)
    public List<Book> getBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Transactional(value = TxType.REQUIRED)
    public boolean removeBookById(Long id) {
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
    @Transactional(value = TxType.SUPPORTS)
    public Book getBookById(Long id) {
        return em.find(Book.class, id);
    }

    /**
     * -------------------------------------
     * 
     * Not implemented:
     * 
     * Handle Exceptions in future commits
     * 
     * -------------------------------------
     * 
     * Update Book in database
     * 
     * @param book
     * @return
     */
    @Transactional(value = TxType.REQUIRED)
    public Book updateBook(Book book) {
        em.merge(book);
        return book;
    }
}