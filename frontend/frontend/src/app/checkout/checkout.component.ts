import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"]
})
export class CheckoutComponent implements OnInit {
  busket: Book[] = [
    new Book(
      "123-456",
      "Learning JavaEE In 10mins",
      "Somebody",
      "it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, ",
      14.5,
      new Date()
    ),
    new Book(
      "456-123",
      "Learning JS In 10mins",
      "NoBody",
      "it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, it is just impossible to learn anything in 10 minutes, ",
      22.12,
      new Date()
    )
  ];

  constructor() {}

  ngOnInit() {}

  /**
   * get total price of all Book displayed on current webpage.
   */
  getTotal(): number {
    let sum: number = 0;
    for (let b of this.busket) {
      sum += b.price;
    }
    return sum;
  }
}
