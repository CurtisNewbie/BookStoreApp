import { Component, OnInit } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { CartItem } from '../model/cartItem';

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"]
})
export class CheckoutComponent implements OnInit {
  cart: Map<number, CartItem>;

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
