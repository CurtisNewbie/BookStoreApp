package com.curtisnewbie.dao;

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

    /**
     * Persist a new Book, the primary key of the Book is set to null before
     * persistence.
     * 
     * @param book the Book to be persisted
     * @return the persisted Book
     * 
     */
    @Transactional(value = TxType.REQUIRED)
    public Book createBook(@NotNull Book book) {
        book.setId(null);
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
     * 
     * Update Book in database
     * 
     * @param book Book to be merged
     * @return updated Book
     * @throws EntityNotFoundException if the Book is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public Book updateBook(Book book) {
        var id = book.getId();
        if (id != null && id >= 0 && em.find(Book.class, id) != null)
            return em.merge(book);
        else
            throw new EntityNotFoundException();
    }
}