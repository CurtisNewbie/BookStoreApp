import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { CheckoutService } from "../checkout.service";
import { FetchBookService } from "../fetch-book.service";

@Component({
  selector: "app-book-detail",
  templateUrl: "./book-detail.component.html",
  styleUrls: ["./book-detail.component.css"]
})
export class BookDetailComponent implements OnInit {
  book: Book;
  constructor(
    private checkoutService: CheckoutService,
    private fetchBookService: FetchBookService
  ) {}

  ngOnInit() {
    this.fetchBook();
  }

  fetchBook() {
    this.book = this.fetchBookService.getBookById("123-456");
  }

  addToCart() {
    this.checkoutService.addToCart(this.book);
  }
}
