import { Injectable } from "@angular/core";
import { Book } from "./model/book";

@Injectable({
  providedIn: "root"
})
export class CheckoutService {
  cart: Map<string, { book: Book; amount: number }> = new Map();

  constructor() {}

  /**
   * Add Book to cart
   * @param book
   */
  addToCart(b: Book): void {
    if (b != undefined) {
      // update if exists
      if (this.cart.has(b.id)) {
        let prev = this.cart.get(b.id);
        this.cart.set(b.id, { book: prev.book, amount: prev.amount + 1 });
      } else {
        // put one in the cart if not exists
        this.cart.set(b.id, { book: b, amount: 1 });
      }
    }
  }

  /**
   * Remove one selected book by Book.id from cart.
   * @param id
   */
  removeFromCart(id: string): void {
    if (this.cart.has(id)) {
      let prev = this.cart.get(id);
      if (prev.amount <= 1) {
        this.cart.delete(id);
      } else {
        this.cart.set(id, { book: prev.book, amount: prev.amount - 1 });
      }
    }
  }

  /**
   * Get the cart. (shallow copy)
   */
  getCart(): Map<string, { book: Book; amount: number }> {
    return this.cart;
  }

  /**
   * Clear cart
   */
  clear(): void {
    this.cart.clear();
  }

  /**
   * Get total price of the cart
   */
  getTotal(): number {
    let sum: number = 0;
    for (let b of this.cart) {
      sum += b[1].book.price * b[1].amount;
    }
    return sum;
  }

  /**
   * Convert the cart (Map) to a list, where each element in the list is an object
   * that contains the book (only with its id) and the amount for this book. The
   * id of each book is enough for the backend to recognise which book it is.
   */
  cartToList(): { book: { id: string }; amount: number }[] {
    // remove unneeded information
    let list: { book: { id: string }; amount: number }[] = [];
    for (let pair of this.cart) {
      list.push({ book: { id: pair[0] }, amount: pair[1].amount });
    }
    return list;
  }
}
