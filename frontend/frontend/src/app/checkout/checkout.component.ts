import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { CheckoutService } from "../checkout.service";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"]
})
export class CheckoutComponent implements OnInit {
  cart: Book[];

  constructor(private checkoutService: CheckoutService) {}

  ngOnInit() {
    this.getCart();
  }

  /**
   * get total price of all Book displayed on current webpage.
   */
  getTotal(): number {
    let sum: number = 0;
    for (let b of this.cart) {
      sum += b.price;
    }
    return sum;
  }

  /**
   * Remove book from cart by its id
   * @param id
   */
  removeFromCart(id: string) {
    this.checkoutService.removeFromCart(id);
  }

  checkout(): void {
    alert("You have successfully checkouted");
    this.checkoutService.clear();
    this.getCart();
  }

  getCart(): void {
    this.cart = this.checkoutService.getCart();
  }
}
