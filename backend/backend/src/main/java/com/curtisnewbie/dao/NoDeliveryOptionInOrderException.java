package com.curtisnewbie.dao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NoDeliveryOptionInOrderException extends WebApplicationException {

    public NoDeliveryOptionInOrderException() {
        super(Response.status(Status.BAD_REQUEST).entity("Order must have one selected delivery option")
                .type(MediaType.TEXT_PLAIN).build());
    }
}