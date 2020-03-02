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

    /**
     * Delete Book By Id
     * 
     * @param id id of the book
     * @throws NotFoundException if Book is not found
     * 
     */
    @Transactional(value = TxType.REQUIRED)
    public void removeBookById(Long id) {
        Book book;
        if (id != null && id >= 0 && (book = em.find(Book.class, id)) != null)
            em.remove(book);
        else
            throw new NotFoundException();
    }

    /**
     * Get Book By Id
     * 
     * @param id id of the book
     * @return the Book that is found
     * @throws NotFoundException if Book is not found
     */
    @Transactional(value = TxType.SUPPORTS)
    public Book getBookById(Long id) {
        Book book;
        if (id != null && id >= 0 && (book = em.find(Book.class, id)) != null)
            return book;
        else
            throw new NotFoundException();
    }

    /**
     * 
     * Update Book in database
     * 
     * @param book Book to be merged
     * @return updated Book
     * @throws NotFoundException if the Book is not found
     */
    @Transactional(value = TxType.REQUIRED)
    public Book updateBook(Book book) {
        var id = book.getId();
        if (id != null && id >= 0 && em.find(Book.class, id) != null)
            return em.merge(book);
        else
            throw new NotFoundException();
    }
}