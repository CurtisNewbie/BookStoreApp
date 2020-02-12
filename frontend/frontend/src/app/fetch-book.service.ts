import { Injectable } from "@angular/core";
import { Book, BEBook } from "./model/book";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class FetchBookService {
  /**
   * GET request URI
   */
  readonly GET_ALL_BOOKS_URL: string = "http://localhost:8080/api/book/all";
  readonly GET_BOOK_BY_ID_URL: string = "http://localhost:8080/api/book";

  constructor(private http: HttpClient) {}

  /** Fetch all books from backend by its id */
  fetchBooks(): Observable<BEBook[]> {
    console.log("[fetch-book.service] - fetching books...");
    return this.http.get<BEBook[]>(this.GET_ALL_BOOKS_URL);
  }

  /**
   * Fetch a book from backend by its id
   * @param id
   */
  fetchBookById(id: string): Observable<BEBook> {
    return this.http.get<BEBook>(this.GET_BOOK_BY_ID_URL, {
      params: { id: id }
    });
  }
}
