import { Injectable } from "@angular/core";
import { Book } from "./model/book";

@Injectable({
  providedIn: "root"
})
export class CheckoutService {
  cart: Book[] = [];

  constructor() {}

  /**
   * Add Book to cart
   * @param book
   */
  addToCart(book: Book): void {
    if (book != undefined) this.cart.push(book);
  }

  /**
   * Remove book by Book.id from cart. If there are multiple books with the same id (the same book), it only removes the first one.
   * @param id
   */
  removeFromCart(id: string): void {
    for (let i = 0; i < this.cart.length; i++) {
      if (this.cart[i].id === id) {
        this.cart.splice(i, 1);
        return;
      }
    }
  }

  /**
   * Get the cart (shallow copy, i.e., the object ref is returned).
   */
  getCart(): Book[] {
    return this.cart;
  }

  /**
   * Clear cart
   */
  clear(): void {
    this.cart = [];
  }

  /**
   * Get total price of the cart
   */
  getTotal(): number {
    let sum: number = 0;
    for (let b of this.cart) {
      sum += b.price;
    }
    return sum;
  }
}
