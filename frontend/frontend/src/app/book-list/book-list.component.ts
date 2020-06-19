import { Component, OnInit } from "@angular/core";
import { Book, BEBook, toBook } from "../model/book";
import { FetchBookService } from "../fetch-book.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-book-list",
  templateUrl: "./book-list.component.html",
  styleUrls: ["./book-list.component.css"]
})
export class BookListComponent implements OnInit {
  books: Book[] = [];

  constructor(private fetchBookService: FetchBookService) { }

  ngOnInit() {
    // fetch all books
    this.fetchBooks();
  }

  /** Fetch all books from backend */
  private fetchBooks(): void {
    this.fetchBookService.fetchBooks().subscribe(
      (books: BEBook[]) => {
        console.log(`Fetched ${books.length} books in total`);
        for (let b of books) {
          // push Book object
          this.books.push(toBook(b));
        }
      },
      (error: HttpErrorResponse) => {
        // log error msg
        console.log(error);
      }
    );
  }
}
