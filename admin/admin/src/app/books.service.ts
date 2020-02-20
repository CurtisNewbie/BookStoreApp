import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Book } from "./model/book";
import { JWTAuthService } from "./jwt-auth.service";

@Injectable({
  providedIn: "root"
})
export class BooksService {
  readonly GET_ALL_BOOKS: string = "http://localhost:8080/api/book/all";
  readonly SINGLE_BOOK_URL: string = "http://localhost:8080/api/book";

  constructor(private http: HttpClient, private jwtAuth: JWTAuthService) {}

  fetchAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.GET_ALL_BOOKS);
  }

  fetchBookById(id: number): Observable<Book> {
    return this.http.get<Book>(this.SINGLE_BOOK_URL + `?id=${id}`);
  }

  /*
  ---------------------------------------

  not yet implemented in backend server

  ---------------------------------------
  */
  fetchBookByTitle(title: string): Observable<Book> {
    return null;
  }

  updateBook(book: Book): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.put(this.SINGLE_BOOK_URL, book, options);
  }

  createBook(book: Book): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.post(this.SINGLE_BOOK_URL, book, options);
  }

  deleteBook(bookId: number): Observable<any> {
    let options: { headers: HttpHeaders; observe; responseType } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response",
      responseType: "text"
    };
    return this.http.delete(this.SINGLE_BOOK_URL + `?id=${bookId}`, options);
  }
}
