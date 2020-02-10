import { Injectable } from "@angular/core";
import { Book } from "./model/book";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class FetchBookService {
  /**
   * GET request URI
   */
  readonly GET_ALL_BOOKS_URL: string = "http://localhost:8080/api/book/all";

  books: Book[] = [];

  constructor(private http: HttpClient) {
    this.fetchBook();
  }

  fetchBook() {
    console.log("[fetch-book.service] - fetching books...");
    let obs = this.http.get(this.GET_ALL_BOOKS_URL);
    obs.subscribe(
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
    // this.books = this.BOOKS_DEMO;
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

/**
 * Modelling the Book JSON object fetched from backend
 */
interface BEBook {
  id: string;
  title: string;
  author: string;
  content: string;
  price: number;
  date: string;
  img: string;
}
