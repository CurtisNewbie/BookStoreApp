import { Component, OnInit, Input } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { Address, emptyAddress } from "../model/address";
import { OrderService } from "../order.service";
import { DeliveryOption } from "../model/deliveryOption";
import { HttpResponse } from "@angular/common/http";
import { OrderReviewService } from "../order-review.service";
import { BEOrder } from "../model/order";
import { Router } from "@angular/router";
import { CartItem } from '../model/cartItem';

@Component({
  selector: "app-order-confirm",
  templateUrl: "./order-confirm.component.html",
  styleUrls: ["./order-confirm.component.css"]
})
export class OrderConfirmComponent implements OnInit {
  delivOpts: DeliveryOption[];
  selectedDelivOption: DeliveryOption;
  cart: Map<number, CartItem>;
  booksPrice: number;
  firstName: string;
  lastName: string;
  deliveryAdd: Address = emptyAddress();

  constructor(
    private checkoutService: CheckoutService,
    private orderService: OrderService,
    private orderReviewService: OrderReviewService,
    private router: Router
  ) { }

  ngOnInit() {
    this.cart = this.checkoutService.getCart();
    this.booksPrice = this.checkoutService.getTotal();
    this.fetchDeliveryOptions();
  }

  sendOrder() {
    // get the list of book and amount in cart, and
    // remove unnecessary information in an order
    let list = this.checkoutService.cartToList();

    //send order to backend via POST method
    this.orderService
      .sendOrder({
        address: this.deliveryAdd,
        books: list,
        firstName: this.firstName,
        lastName: this.lastName,
        deliveryOptionId: this.selectedDelivOption.id
      })
      .subscribe(
        (resp: HttpResponse<BEOrder>) => {
          alert(
            "Done! your order has been successfully created on the server."
          );
          // if the POST request is successful, show the component to review the order that is successfully created.
          let orderSent: BEOrder = resp.body;
          this.orderReviewService.showOrderSentComponent(orderSent);
        },
        error => {
          alert(
            "Your order cannot be sent to the server. Error code: " +
            error.status
          );
          console.log("Failed to POST Order, error :", error);
          this.router.navigateByUrl("/home");
        }
      );
    // clear cart
    this.checkoutService.clear();
  }

  /**
   * Select one of the radio button (delivery option)
   *
   * @param n index of the selected delivery option
   */
  selectRadioButton(index: number): void {
    if (index >= 0 && index < this.delivOpts.length)
      this.selectedDelivOption = this.delivOpts[index];
  }

  /** Check whether all requred inputs are completed properly */
  isCompleted(): boolean {
    if (
      this.cart.size &&
      this.selectedDelivOption &&
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

  /** Fetch all delivery options from backend */
  private fetchDeliveryOptions(): void {
    this.orderService.fetchDeliveryOptions().subscribe({
      next: options => {
        this.delivOpts = options;
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
