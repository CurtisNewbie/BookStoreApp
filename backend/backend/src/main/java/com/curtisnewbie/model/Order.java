package com.curtisnewbie.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Representation of Order */
@Entity
@Table(name = "BS_Order")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate date;

    @NotNull
    @Embedded
    @Valid
    private Address address;

    @NotNull
    private double price;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookOrder> booksOnOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private DeliveryOption deliveryOption;

    public Order() {
    }

    public void addBookOrder(BookOrder b) {
        this.booksOnOrder.add(b);
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
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
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
    public List<BookOrder> getBooksOnOrder() {
        return booksOnOrder;
    }

    /**
     * @param booksOnOrder the booksOnOrder to set
     */
    public void setBooksOnOrder(List<BookOrder> booksOnOrder) {
        this.booksOnOrder = booksOnOrder;
    }

    /**
     * @return the deliveryOption
     */
    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    /**
     * @param deliveryOption the deliveryOption to set
     */
    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}