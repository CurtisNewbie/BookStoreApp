import { Component, OnInit } from "@angular/core";
import { DeliveryOption } from "../model/deliveryOption";

@Component({
  selector: "app-delivery-option",
  templateUrl: "./delivery-option.component.html",
  styleUrls: ["./delivery-option.component.css"]
})
export class DeliveryOptionComponent implements OnInit {
  // demo data
  deliveryOptions: DeliveryOption[] = [
    {
      id: 1,
      name: "Next Day Delivery (Order before 2pm)",
      price: 5
    },
    {
      id: 2,
      name: "3 - 5 Days Delivery",
      price: 3.2
    },
    {
      id: 3,
      name: "One Week Delivery",
      price: 2.6
    }
  ];
  selectedDeliveryOpt: DeliveryOption;

  constructor() {}

  ngOnInit() {}

  selectDeliveryOpt(index: number) {
    this.selectedDeliveryOpt = this.deliveryOptions[index];
  }

  createDeliveryOptTemplate() {
    this.selectedDeliveryOpt = {
      id: null,
      name: "",
      price: null
    };
  }

  deleteDeliveryOpt(opt: DeliveryOption) {
    // DELETE request
  }

  createDeliveryOpt(opt: DeliveryOption) {
    // POST request
  }

  updateDeliveryOpt(opt: DeliveryOption) {
    // PUT request
  }
}
