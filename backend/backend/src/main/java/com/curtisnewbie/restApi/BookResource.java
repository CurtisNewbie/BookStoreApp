package com.curtisnewbie.restApi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import com.curtisnewbie.dto.BookDTO;
import com.curtisnewbie.model.*;
import com.curtisnewbie.util.*;

/**
 * RESTful api for Book Resources
 */
@Path("book")
public class BookResource {

    @EJB
    private BookRepository bookRepo;

    // http://localhost:8080/api/book?id=123-456
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@QueryParam("id") String id) {
        var book = bookRepo.getBookById(id);
        if (book != null)
            return Response.ok(new BookDTO(book)).build();
        else
            return Response.noContent().build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        var books = bookRepo.getBooks();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book b : books) {
            bookDTOs.add(new BookDTO(b));
        }
        return Response.ok(bookDTOs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        String id = book.getId();
        if (id != null && !id.isEmpty()) {
            bookRepo.createBook(book);
            return Response.created(
                    UriBuilder.fromPath("http://localhost:8080/api/book/").queryParam("id", book.getId()).build())
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    /*
     * -------------------------------------
     * 
     * Not Implemented
     * 
     * -------------------------------------
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        // for testing
        String id;
        if ((id = book.getId()) != null && id.equals("123-456")) {
            book.setTitle("put updated");
            return Response.ok(book).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(@QueryParam("id") String id) {
        if (id != null && !id.isEmpty() && bookRepo.removeBookById(id))
            return Response.ok("Book id:" + id + " deleted").build();
        else
            return Response.noContent().build();
    }

}