package com.curtisnewbie.restApi;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import com.curtisnewbie.model.*;
import com.curtisnewbie.util.*;
import com.curtisnewbie.security.*;

/**
 * RESTful api for Book Resources
 */
@Path("book")
@RequestScoped
public class BookResource {

    @EJB
    private BookRepository bookRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@QueryParam("id") Long id) {
        var book = bookRepo.getBookById(id);
        if (book != null)
            return Response.ok(book).build();
        else
            return Response.noContent().build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        var books = bookRepo.getBooks();
        return Response.ok(books).build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        book = bookRepo.createBook(book);
        return Response
                .created(UriBuilder.fromPath("http://localhost:8080/api/book/").queryParam("id", book.getId()).build())
                .build();
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        Long id = book.getId();
        if (id != null && id >= 0) {
            book = bookRepo.updateBook(book);
            return Response.ok(book).build();
        } else {
            return Response.noContent().build();
        }
    }

    @RolesAllowed(SecurityRole.ADMIN)
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(@QueryParam("id") Long id) {
        if (id != null && id >= 0 && bookRepo.removeBookById(id))
            return Response.ok("Book id:" + id + " deleted").build();
        else
            return Response.noContent().build();
    }

}