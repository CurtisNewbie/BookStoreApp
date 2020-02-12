import { Component, OnInit } from "@angular/core";
import { Book, BEBook } from "../model/book";
import { FetchBookService } from "../fetch-book.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-book-list",
  templateUrl: "./book-list.component.html",
  styleUrls: ["./book-list.component.css"]
})
export class BookListComponent implements OnInit {
  books: Book[] = [];

  constructor(private fetchBookService: FetchBookService) {}

  ngOnInit() {
    this.fetchBooks();
  }

  /** Fetch all books from backend */
  fetchBooks() {
    this.fetchBookService.fetchBooks().subscribe(
      (books: BEBook[]) => {
        for (let b of books) {
          // push Book object
          this.books.push({
            id: b.id,
            title: b.title,
            author: b.author,
            content: b.content,
            price: b.price,
            date:
              b.date !== undefined
                ? new Date(
                    parseInt(b.date.substring(0, 4)),
                    parseInt(b.date.substring(5, 7)) - 1,
                    parseInt(b.date.substring(8, 10))
                  )
                : null,
            img: b.img !== undefined ? b.img : null
          });
        }
      },
      (error: HttpErrorResponse) => {
        // log error msg
        console.log(error);
      }
    );
  }
}
