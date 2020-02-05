import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { CheckoutService } from "../checkout.service";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"]
})
export class CheckoutComponent implements OnInit {
  busket: Book[];

  constructor(private checkoutService: CheckoutService) {}

  ngOnInit() {
    this.getBusket();
  }

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

  /**
   * Remove book from busket by its id
   * @param id
   */
  removeFromBusket(id: string) {
    this.checkoutService.removeFromBusket(id);
  }

  checkout(): void {
    alert("You have successfully checkouted");
    this.checkoutService.clear();
    this.getBusket();
  }

  getBusket(): void {
    this.busket = this.checkoutService.getBusket();
  }
}
