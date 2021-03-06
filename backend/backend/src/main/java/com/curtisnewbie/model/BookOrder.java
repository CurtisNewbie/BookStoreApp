package com.curtisnewbie.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/** This acts as a join table between Book and Order */
@Entity
@Table(name = "BS_Book_Order")
public class BookOrder {

    @Id
    @GeneratedValue
    @JsonbTransient
    private long id;
    /** Amount of this book in this order */
    @Min(value = 1)
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Book book;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}