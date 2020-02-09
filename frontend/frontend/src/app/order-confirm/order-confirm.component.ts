import { Component, OnInit, Input } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { Book } from "../model/book";
import { Router } from "@angular/router";
import { Address } from "../model/address";
import { Contact } from "../model/contact";
import { OrderService } from "../order.service";

@Component({
  selector: "app-order-confirm",
  templateUrl: "./order-confirm.component.html",
  styleUrls: ["./order-confirm.component.css"]
})
export class OrderConfirmComponent implements OnInit {
  /* 
  --------------------------------------------
  
  Consider fetching these schemes from backend 

  --------------------------------------------
  */
  readonly SAME_DAY = 5;
  readonly THREE_TO_FIVE_DAYS = 3;
  readonly ONE_WEEK = 2.2;

  booksPrice: number;
  deliveryPrice: number;
  cart: Book[];
  deliveryAdd: Address = {
    city: "",
    county: "",
    firstLine: "",
    secondLine: "",
    postcode: ""
  };
  contact: Contact = {
    firstName: "",
    lastName: ""
  };

  constructor(
    private checkoutService: CheckoutService,
    private router: Router,
    private orderService: OrderService
  ) {}

  ngOnInit() {
    this.cart = this.checkoutService.getCart();
    this.booksPrice = this.checkoutService.getTotal();
  }

  sendOrder() {
    this.checkoutService.clear();
    // remove unneeded information
    let idOfBooks: { id: string }[] = [];
    for (let b of this.cart) {
      idOfBooks.push({ id: b.id });
    }
    //send order to backend via rest
    this.orderService.sendOrder({
      address: this.deliveryAdd,
      booksOnOrder: idOfBooks,
      firstName: this.contact.firstName,
      lastName: this.contact.lastName
    });
    // redirect to the home-page component
    this.router.navigateByUrl("/home");
  }

  /**
   * Select one of the radio button
   *
   * @param n price of selected delivery schema
   */
  selectRadioButton(n: number): void {
    this.deliveryPrice = n;
  }

  /** Check whether all requred inputs are completed properly */
  isComplete(): boolean {
    if (
      this.cart.length &&
      this.deliveryPrice &&
      this.deliveryAdd.firstLine &&
      this.deliveryAdd.city &&
      this.deliveryAdd.county &&
      this.deliveryAdd.postcode &&
      this.contact.firstName &&
      this.contact.lastName
    )
      return true;
    else return false;
  }
}
