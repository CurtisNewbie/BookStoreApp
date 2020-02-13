import { Injectable } from "@angular/core";
import { BEOrder } from "./model/order";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
/** Navigate to order-sent.component to show the details of the Order that is successfully sent */
export class OrderReviewService {
  orderSent;
  constructor(private router: Router) {}

  /**
   * Show order-sent.component to review the order created
   */
  showOrderSentComponent(orderCreated: BEOrder) {
    if (orderCreated === null) {
      this.router.navigateByUrl("/home");
    } else {
      this.setOrderSent(orderCreated);
      // redirect to the order-sent.component to review the order that is sent successfully
      this.router.navigateByUrl("/order/sent");
    }
  }

  getOrderSent() {
    return this.orderSent;
  }

  /** the values in orderCreated to orderSent */
  setOrderSent(orderCreated: BEOrder) {
    this.orderSent = orderCreated;
  }
}
