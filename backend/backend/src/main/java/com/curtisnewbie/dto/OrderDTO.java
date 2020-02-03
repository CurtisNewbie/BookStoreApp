package com.curtisnewbie.dto;

import java.util.Date;
import java.util.List;

import com.curtisnewbie.model.Address;
import com.curtisnewbie.model.Book;
import com.curtisnewbie.model.Order;

public class OrderDTO {

    private Long orderId;
    private String firstName;
    private String lastName;
    private Date date;
    private Address address;
    private List<Book> booksOnOrder;

    public OrderDTO() {

    }

    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.firstName = order.getFirstName();
        this.lastName = order.getLastName();
        this.date = order.getDate();
        this.address = order.getAddress();
        this.booksOnOrder = order.getBooksOnOrder();
    }

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