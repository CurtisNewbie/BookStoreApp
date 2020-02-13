import { Component, OnInit } from "@angular/core";
import { OrderReviewService } from "../order-review.service";
import { BEOrder } from "../model/order";

@Component({
  selector: "app-order-sent",
  templateUrl: "./order-sent.component.html",
  styleUrls: ["./order-sent.component.css"]
})
export class OrderSentComponent implements OnInit {
  orderSent: BEOrder;

  constructor(private orderReviewService: OrderReviewService) {}

  ngOnInit() {
    this.orderSent = this.orderReviewService.getOrderSent();
  }
}
