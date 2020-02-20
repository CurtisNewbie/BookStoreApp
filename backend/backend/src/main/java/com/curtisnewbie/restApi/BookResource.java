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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.security.SecurityRole;
import com.curtisnewbie.util.BookRepository;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

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
    @Operation(summary = "Get a book by its id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)), description = "A book of the given id"),
            @APIResponse(responseCode = "204", description = "Book Not Found") })
    @Parameter(required = true, description = "Book id", schema = @Schema(type = SchemaType.NUMBER), in = ParameterIn.QUERY)
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
    @Operation(summary = "Get all books")
    @APIResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)), description = "Return all books in an array")
    public Response getAllBooks() {
        var books = bookRepo.getBooks();
        return Response.ok(books).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRole.ADMIN)
    @Operation(summary = "Create a book")
    @APIResponse(responseCode = "201", description = "Book created, a URI for the created Book is returned in response hearder - 'location'")
    @RequestBody(description = "Book to be created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT", description = "A JWT generated with role \"admin\"")
    public Response createBook(Book book) {
        book = bookRepo.createBook(book);
        return Response
                .created(UriBuilder.fromPath("http://localhost:8080/api/book/").queryParam("id", book.getId()).build())
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRole.ADMIN)
    @SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT", description = "A JWT generated with role \"admin\"")
    @Operation(summary = "Update all details of an existing book", description = "If the book is found, all of its details in database will be updated")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Book updated and returned.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "204", description = "Book is not updated as id is illegal.") })
    @RequestBody(description = "Book to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    public Response updateBook(Book book) {
        Long id = book.getId();
        if (id != null && id >= 0) {
            book = bookRepo.updateBook(book);
            return Response.ok(book).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(SecurityRole.ADMIN)
    @SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT", description = "A JWT generated with role \"admin\"")
    @Operation(summary = "Delete a book by its id", description = "If the book is found and there is no existing order purchasing this book, this book is removed.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "The book of this id is removed, a message about this is returned", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "204", description = "Book cannot be removed.") })
    @Parameter(required = true, description = "Bbook id", schema = @Schema(type = SchemaType.NUMBER), in = ParameterIn.QUERY)
    public Response deleteBook(@QueryParam("id") Long id) {
        if (id != null && id >= 0 && bookRepo.removeBookById(id))
            return Response.ok("Book id:" + id + " deleted").build();
        else
            return Response.noContent().build();
    }

}