import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { FetchBookService } from "../fetch-book.service";

@Component({
  selector: "app-book-list",
  templateUrl: "./book-list.component.html",
  styleUrls: ["./book-list.component.css"]
})
export class BookListComponent implements OnInit {
  books: Book[];

  constructor(private fetchBookService: FetchBookService) {}

  ngOnInit() {
    this.fetchBooks();
  }

  fetchBooks() {
    this.books = this.fetchBookService.getAllBooks();
  }
}
