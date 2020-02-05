import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { CheckoutService } from "../checkout.service";

@Component({
  selector: "app-book-detail",
  templateUrl: "./book-detail.component.html",
  styleUrls: ["./book-detail.component.css"]
})
export class BookDetailComponent implements OnInit {
  book: Book;
  constructor(private checkoutService: CheckoutService) {}

  ngOnInit() {
    this.fetchHero();
  }

  fetchHero() {
    this.book = new Book(
      "123-456",
      "Learning JavaEE In 10mins",
      "Somebody",
      "it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, ",
      14.5,
      new Date()
    );
  }

  addToBusket() {
    this.checkoutService.addToBusket(this.book);
  }
}
