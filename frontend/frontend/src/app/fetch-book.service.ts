import { Injectable } from "@angular/core";
import { Book, BEBook } from "./model/book";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { config } from "src/environments/config";

@Injectable({
  providedIn: "root"
})
export class FetchBookService {
  /**
   * GET request URI
   */
  readonly GET_ALL_BOOKS_URL: string = `http://${config.backend.hostname}:${config.backend.port}/api/book/all`;
  readonly GET_BOOK_BY_ID_URL: string = `http://${config.backend.hostname}:${config.backend.port}/api/book`;

  constructor(private http: HttpClient) {}

  /** Fetch all books from backend by its id */
  fetchBooks(): Observable<BEBook[]> {
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
