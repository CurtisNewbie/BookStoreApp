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
    // // demo data
    // this.book.img =
    //   "https://images-na.ssl-images-amazon.com/images/I/51vJRPD1sxL._SX385_BO1,204,203,200_.jpg";
  }

  fetchBook() {
    const id: string = this.route.snapshot.queryParamMap.get("id");
    if (id !== null) this.book = this.fetchBookService.getBookById(id);
  }

  addToCart() {
    this.checkoutService.addToCart(this.book);
  }
}
