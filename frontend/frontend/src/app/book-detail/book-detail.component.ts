import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { CheckoutService } from "../checkout.service";
import { FetchBookService } from "../fetch-book.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-book-detail",
  templateUrl: "./book-detail.component.html",
  styleUrls: ["./book-detail.component.css"]
})
export class BookDetailComponent implements OnInit {
  book: Book;
  constructor(
    private checkoutService: CheckoutService,
    private fetchBookService: FetchBookService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.fetchBook();
  }

  fetchBook() {
    const id: string = this.route.snapshot.queryParamMap.get("id");
    if (id !== null) this.book = this.fetchBookService.getBookById(id);
  }

  addToCart() {
    this.checkoutService.addToCart(this.book);
  }
}
