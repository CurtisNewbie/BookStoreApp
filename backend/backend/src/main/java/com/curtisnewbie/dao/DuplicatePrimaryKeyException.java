package com.curtisnewbie.dao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Primary Key is found to be violated when an instance is being persisted.
 */
public class DuplicatePrimaryKeyException extends WebApplicationException {

    public DuplicatePrimaryKeyException() {
        super(Response.status(Status.BAD_REQUEST).entity("Primary key integrity violated, instance not created.")
                .type(MediaType.TEXT_PLAIN).build());
    }
}