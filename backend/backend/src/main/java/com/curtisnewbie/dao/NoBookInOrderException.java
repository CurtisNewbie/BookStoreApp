package com.curtisnewbie.dao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NoBookInOrderException extends WebApplicationException {

    private static final long serialVersionUID = -3461542580998772849L;

    NoBookInOrderException() {
        super(Response.status(Status.BAD_REQUEST).entity("Order must have at least one book").type(MediaType.TEXT_PLAIN)
                .build());
    }
}