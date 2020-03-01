package com.curtisnewbie.dao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * The instance is not found when an EntityManager is trying to merge it.
 */
public class EntityNotFoundException extends WebApplicationException {

    public EntityNotFoundException() {
        super(Response.status(Status.BAD_REQUEST)
                .entity("Instance is not found or has been removed, it cannot be updated.").type(MediaType.TEXT_PLAIN)
                .build());
    }
}