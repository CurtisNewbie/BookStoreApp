package com.curtisnewbie.util;

import java.util.List;

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
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createBook(@NotNull Book book) {
        em.persist(book);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Book> getBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

}