package com.curtisnewbie.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.curtisnewbie.dto.BookDTO;
import com.curtisnewbie.dto.OrderDTO;

/** Representation of Order */
@Entity
@Table(name = "BookStore_Order")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Date date;

    @NotNull
    @Embedded
    private Address address;

    // Order is the owner of the relationship
    @ManyToMany
    @JoinTable(name = "book_order", joinColumns = @JoinColumn(name = "order_fk"), inverseJoinColumns = @JoinColumn(name = "book_fk"))
    private List<Book> booksOnOrder;

    public Order() {
    }

    public Order(OrderDTO orderDTO) {
        this.orderId = orderDTO.getOrderId();
        this.firstName = orderDTO.getFirstName();
        this.lastName = orderDTO.getLastName();
        this.date = orderDTO.getDate();
        this.address = orderDTO.getAddress();

        var l = orderDTO.getBooksOnOrder();
        this.booksOnOrder = new ArrayList<>();
        for (BookDTO b : l) {
            this.booksOnOrder.add(new Book(b));
        }
    }

    /*
     * 
     * ----------------------------------------------------
     * 
     * 
     * Relation With Book is not yet implemented!!!!
     * 
     * 
     * ----------------------------------------------------
     * 
     */

    /**
     * @return the orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the booksOnOrder
     */
    public List<Book> getBooksOnOrder() {
        return booksOnOrder;
    }

    /**
     * @param booksOnOrder the booksOnOrder to set
     */
    public void setBooksOnOrder(List<Book> booksOnOrder) {
        this.booksOnOrder = booksOnOrder;
    }

}