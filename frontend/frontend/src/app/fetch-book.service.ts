import { Injectable } from "@angular/core";
import { Book } from "./model/book";

@Injectable({
  providedIn: "root"
})
export class FetchBookService {
  readonly BOOKS_DEMO = [
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

  books: Book[];

  constructor() {
    this.fetchBook();
  }

  fetchBook() {
    console.log("FetchBook Service...fetching...");
    this.books = this.BOOKS_DEMO;
  }

  /**
   * Get the whole list of books (shallow copy)
   */
  getAllBooks(): Book[] {
    return this.books;
  }

  /**
   * Get a book by its id
   * @param id
   */
  getBookById(id: string): Book {
    for (let b of this.books) {
      if (b.id === id) return b;
    }
  }
}
