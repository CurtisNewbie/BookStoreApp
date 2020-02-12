import { Component, OnInit, Input } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { Book } from "../model/book";
import { Router } from "@angular/router";
import { Address } from "../model/address";
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

  cart: Map<string, { book: Book; amount: number }>;
  booksPrice: number;
  deliveryPrice: number;
  firstName: string;
  lastName: string;
  deliveryAdd: Address = {
    city: "",
    county: "",
    firstLine: "",
    secondLine: "",
    postCode: ""
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
    // get the list of book and amount in cart, and
    // remove unnecessary information in an order
    let list = this.checkoutService.cartToList();

    //send order to backend via rest
    this.orderService.sendOrder({
      address: this.deliveryAdd,
      booksOnOrder: list,
      firstName: this.firstName,
      lastName: this.lastName
    });

    // redirect to the home-page component
    this.checkoutService.clear();
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
      this.cart.size &&
      this.deliveryPrice &&
      this.deliveryAdd.firstLine &&
      this.deliveryAdd.city &&
      this.deliveryAdd.county &&
      this.deliveryAdd.postCode &&
      this.firstName &&
      this.lastName
    )
      return true;
    else return false;
  }
}
