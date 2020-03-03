import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { BooksService } from "../books.service";
import { JWTAuthService } from "../jwt-auth.service";
import { Router } from "@angular/router";
import { HttpResponse } from "@angular/common/http";
import { Refreshable } from "../refreshable";

@Component({
  selector: "app-books",
  templateUrl: "./books.component.html",
  styleUrls: ["./books.component.css"]
})
export class BooksComponent implements OnInit, Refreshable {
  books: Book[];
  selectedBook: Book;
  // indicate whether the user is creating a new book
  creatingBook: boolean = false;
  constructor(
    private booksService: BooksService,
    private jwtAuth: JWTAuthService
  ) {}

  ngOnInit() {
    this.getAllBooks();
    this.jwtAuth.registerCurrPage(this);
  }

  createBookTemplate() {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }
    this.selectedBook = {
      author: "",
      content: "",
      date: "",
      id: null,
      img: "",
      price: null,
      title: ""
    };
  }
  /** Select one that is displayed */
  selectBook(book: Book) {
    this.selectedBook = Object.assign({}, book);
  }

  updateBook(book: Book) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.booksService.updateBook(book).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200) console.log(`Failed to update "${book.id}"`);
        else console.log(`Successfully updated "${book.id}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  createBook(book: Book) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.booksService.createBook(book).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 201) console.log(`Failed to create "${book.id}"`);
        else console.log(`Successfully created "${book.id}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  deleteBook(book: Book) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.booksService.deleteBook(book.id).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200) console.log(`Failed to delete "${book.id}"`);
        else console.log(resp.body);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  getAllBooks() {
    this.booksService.fetchAllBooks().subscribe((allBooks: Book[]) => {
      this.books = allBooks;
    });
  }

  refresh() {
    this.books = null;
    this.selectedBook = null;
    this.getAllBooks();
  }
}
