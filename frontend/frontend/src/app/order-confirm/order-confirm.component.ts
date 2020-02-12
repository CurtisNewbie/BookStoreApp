import { Component, OnInit, Input } from "@angular/core";
import { CheckoutService } from "../checkout.service";
import { Book } from "../model/book";
import { Router } from "@angular/router";
import { Address } from "../model/address";
import { OrderService } from "../order.service";
import { DeliveryOption } from "../model/deliveryOption";

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
    private router: Router,
    private orderService: OrderService
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

    //send order to backend via rest
    this.orderService.sendOrder({
      address: this.deliveryAdd,
      booksOnOrder: list,
      firstName: this.firstName,
      lastName: this.lastName,
      deliveryOption: { id: this.selectedDelivOption.id }
    });

    // redirect to the home-page component
    this.checkoutService.clear();
    this.router.navigateByUrl("/home");
  }

  /**
   * Select one of the radio button (delivery option)
   *
   * @param n index of the selected delivery option
   */
  selectRadioButton(index: number): void {
    if (index >= 0 && index < this.delivOpts.length)
      this.selectedDelivOption = this.delivOpts[index];
    console.log(this.selectedDelivOption);
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
