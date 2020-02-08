import { Component, OnInit, Input } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { Book } from "../model/book";
import { Router } from "@angular/router";

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

  deliveryPrice: number;
  booksPrice: number;
  cart: Book[];
  constructor(
    private checkoutService: CheckoutService,
    private router: Router
  ) {}

  ngOnInit() {
    this.cart = this.checkoutService.getCart();
    this.booksPrice = this.checkoutService.getTotal();
  }

  confirmOrder() {
    this.checkoutService.clear();
    /*
    send order to backend via rest
     */
    alert("Done! your order has been sent to the server");
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
}
