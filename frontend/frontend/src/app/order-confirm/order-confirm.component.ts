import { Component, OnInit, Input } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { Book, BEBook } from "../model/book";
import { Address } from "../model/address";
import { OrderService } from "../order.service";
import { DeliveryOption } from "../model/deliveryOption";
import { HttpResponse } from "@angular/common/http";
import { OrderReviewService } from "../order-review.service";
import { BEOrder } from "../model/order";
import { Router } from "@angular/router";

@Component({
  selector: "app-order-confirm",
  templateUrl: "./order-confirm.component.html",
  styleUrls: ["./order-confirm.component.css"]
})
export class OrderConfirmComponent implements OnInit {
  delivOpts: DeliveryOption[];
  selectedDelivOption: DeliveryOption;
  cart: Map<string, { book: Book; amount: number }>;
  booksPrice: number;
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
    private orderService: OrderService,
    private orderRevewService: OrderReviewService,
    private router: Router
  ) {}

  ngOnInit() {
    this.cart = this.checkoutService.getCart();
    this.booksPrice = this.checkoutService.getTotal();
    this.fetchDeliveryOptions();
  }

  sendOrder() {
    // get the list of book and amount in cart, and
    // remove unnecessary information in an order
    let list = this.checkoutService.cartToList();

    let orderSent: BEOrder;
    //send order to backend via POST method
    this.orderService
      .sendOrder({
        address: this.deliveryAdd,
        booksOnOrder: list,
        firstName: this.firstName,
        lastName: this.lastName,
        deliveryOption: { id: this.selectedDelivOption.id }
      })
      .subscribe(
        (resp: HttpResponse<BEOrder>) => {
          alert(
            "Done! your order has been successfully created on the server."
          );
          let o: BEOrder = resp.body;
          // map response to orderSent object
          orderSent = {
            address: o.address,
            booksOnOrder: o.booksOnOrder,
            date: o.date,
            deliveryOption: o.deliveryOption,
            firstName: o.firstName,
            lastName: o.lastName,
            orderId: o.orderId,
            price: o.price
          };

          // if the POST request is successful, show the component to review the order that is successfully created.
          this.orderRevewService.showOrderSentComponent(orderSent);
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
  isComplete(): boolean {
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
  fetchDeliveryOptions(): void {
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
