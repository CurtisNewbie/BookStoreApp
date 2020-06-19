import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";
import { CheckoutService } from "../checkout.service";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"]
})
export class CheckoutComponent implements OnInit {
  cart: Map<number, { book: Book; amount: number }>;

  constructor(private checkoutService: CheckoutService) { }

  ngOnInit() {
    this.getCart();
  }

  /**
   * get total price of all Book displayed on current webpage.
   */
  getTotal(): number {
    return this.checkoutService.getTotal();
  }

  /**
   * Remove book from cart by its id
   * @param id
   */
  removeFromCart(id: number): void {
    this.checkoutService.removeFromCart(id);
  }

  /**
   * Get the cart from checkoutService
   */
  private getCart(): void {
    this.cart = this.checkoutService.getCart();
  }
}
