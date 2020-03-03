import { Component, OnInit } from "@angular/core";
import { DeliveryOption } from "../model/deliveryOption";
import { JWTAuthService } from "../jwt-auth.service";
import { DeliveryOptionsService } from "../delivery-options.service";
import { HttpResponse } from "@angular/common/http";
import { Refreshable } from "../refreshable";

@Component({
  selector: "app-delivery-option",
  templateUrl: "./delivery-option.component.html",
  styleUrls: ["./delivery-option.component.css"]
})
export class DeliveryOptionComponent implements OnInit, Refreshable {
  // demo data
  deliveryOptions: DeliveryOption[];
  selectedDeliveryOpt: DeliveryOption;

  constructor(
    private jwtAuth: JWTAuthService,
    private delivOptService: DeliveryOptionsService
  ) {}

  ngOnInit() {
    this.getAllDeliveryOptions();
    this.jwtAuth.registerCurrPage(this);
  }

  /** Select one that is displayed */
  selectDeliveryOpt(delivOpt: DeliveryOption) {
    this.selectedDeliveryOpt = Object.assign({}, delivOpt);
  }

  createDeliveryOptTemplate() {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }
    this.selectedDeliveryOpt = {
      id: null,
      name: "",
      price: null
    };
  }

  deleteDeliveryOpt(opt: DeliveryOption) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.delivOptService.deleteDeliveryOpt(opt.id).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200) console.log(`Failed to delete "${opt.id}"`);
        else console.log(`"${resp.body}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  createDeliveryOpt(opt: DeliveryOption) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    /* id is created in backend server */
    let tempDTO: DeliveryOption = {
      name: opt.name,
      price: opt.price
    };
    this.delivOptService.createDeliveryOpt(tempDTO).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 201) console.log(`Failed to create "${opt.name}"`);
        else console.log(`Successfully created "${opt.name}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  updateDeliveryOpt(opt: DeliveryOption) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.delivOptService.updateDeliveryOpt(opt).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200) console.log(`Failed to update "${opt.id}"`);
        else console.log(`Successfully updated "${opt.id}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  getAllDeliveryOptions() {
    this.delivOptService
      .fetchAllDeliveryOpt()
      .subscribe((val: DeliveryOption[]) => {
        this.deliveryOptions = val;
      });
  }

  /**
   * Clear previous storage of deliveryOptions, retrieve these data again from backend server.
   */
  refresh() {
    this.deliveryOptions = null;
    this.selectedDeliveryOpt = null;
    this.getAllDeliveryOptions();
  }
}
