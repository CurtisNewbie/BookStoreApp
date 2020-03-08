package com.curtisnewbie.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.curtisnewbie.model.Book;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class BookRepositoryTest {

    @Inject
    BookRepository bookRepo;

    @Test
    @Order(1)
    public void shouldThrowExcpetion() {
        var id = 123456789987654321l;
        assertThrows(WebApplicationException.class, () -> {
            bookRepo.getBookById(id);
        });
        assertThrows(WebApplicationException.class, () -> {
            bookRepo.removeBookById(id);
        });
        assertThrows(WebApplicationException.class, () -> {
            Book book = new Book();
            book.setId(id);
            book.setTitle("Dummy Title");
            book.setPrice(12345.54321);
            book.setDate(LocalDate.now());
            book.setContent("Blablabla");
            bookRepo.updateBook(book);
        });
    }

    @Test
    @Order(2)
    public void shouldCreateBook() {
        var title = "Dummy Title";
        var price = 12345.54321;
        var now = LocalDate.now();
        var content = "Blablabla";

        Book book = new Book();
        book.setTitle(title);
        book.setPrice(price);
        book.setDate(now);
        book.setContent(content);

        var createdBook = bookRepo.createBook(book);
        assertNotNull(createdBook);
        assertNotNull(createdBook.getId());
        assertEquals(title, createdBook.getTitle());
        assertEquals(price, createdBook.getPrice());
        assertEquals(now, createdBook.getDate());
        assertEquals(content, createdBook.getContent());
    }

    @Test
    @Order(3)
    public void shouldUpdateBook() {
        var books = bookRepo.getBooks();
        assertTrue(books.size() > 0);
        var book = books.get(0);
        assertNotNull(book);

        // change content
        var sameId = book.getId();
        var diffCont = book.getContent() + "Different";
        var diffPrice = book.getPrice() + 10;
        var diffDate = book.getDate().minusDays(1);
        var diffTitle = book.getTitle() + "Different";
        book = new Book();
        book.setId(sameId);
        book.setContent(diffCont);
        book.setPrice(diffPrice);
        book.setDate(diffDate);
        book.setTitle(diffTitle);

        var bookUpdated = bookRepo.updateBook(book);
        assertNotNull(bookUpdated);
        assertEquals(diffCont, bookUpdated.getContent());
        assertEquals(diffPrice, bookUpdated.getPrice());
        assertEquals(diffDate, bookUpdated.getDate());
        assertEquals(diffTitle, bookUpdated.getTitle());
    }

    @Test
    @Order(4)
    public void shouldDeleteBook() {
        var books = bookRepo.getBooks();
        assertTrue(books.size() > 0);
        var book = books.get(0);
        assertNotNull(book);
        bookRepo.removeBookById(book.getId());
    }

}
