import { Injectable } from "@angular/core";
import { Book } from "./model/book";

@Injectable({
  providedIn: "root"
})
export class CheckoutService {
  busket: Book[] = [];

  constructor() {}

  /**
   * Add Book to busket
   * @param book
   */
  addToBusket(book: Book): void {
    if (book != undefined) this.busket.push(book);
  }

  /**
   * Remove book by Book.id from busket. If there are multiple books with the same id (the same book), it only removes the first one.
   * @param id
   */
  removeFromBusket(id: string): void {
    for (let i = 0; i < this.busket.length; i++) {
      if (this.busket[i].id === id) {
        this.busket.splice(i, 1);
        return;
      }
    }
  }

  /**
   * Get the busket (shallow copy, i.e., the object ref is returned).
   */
  getBusket(): Book[] {
    return this.busket;
  }
}
