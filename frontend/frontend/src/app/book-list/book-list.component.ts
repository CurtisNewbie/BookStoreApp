import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";

@Component({
  selector: "app-book-list",
  templateUrl: "./book-list.component.html",
  styleUrls: ["./book-list.component.css"]
})
export class BookListComponent implements OnInit {
  books: Book[];

  constructor() {}

  ngOnInit() {
    this.fetchBooks();
  }

  fetchBooks() {
    this.books = [
      new Book(
        "123-456",
        "Learning JavaEE In 10mins",
        "Somebody",
        "it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, ",
        14.5,
        new Date()
      ),
      new Book(
        "456-123",
        "Learning JS In 10mins",
        "NoBody",
        "it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, ",
        22.12,
        new Date()
      )
    ];
  }
}
