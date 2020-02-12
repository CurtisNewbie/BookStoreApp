import { Injectable } from "@angular/core";
import {
  HttpClient,
  HttpHeaders,
  HttpResponse,
  HttpEvent
} from "@angular/common/http";
import { Order } from "./model/order";
import { DeliveryOption } from "./model/deliveryOption";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class OrderService {
  readonly POST_ORDER_URL = "http://localhost:8080/api/order";
  readonly GET_DELIV_IRL = "http://localhost:8080/api/delivery/option/all";
  readonly httpOptions: { headers: HttpHeaders; observe } = {
    headers: new HttpHeaders({
      "Content-Type": "application/json"
    }),
    observe: "response"
  };

  constructor(private http: HttpClient) {
    this.fetchDeliveryOptions();
  }

  /** Send the order via the POST method to the backend server */
  sendOrder(order: Order): Observable<any> {
    console.log("Sending Order", order);
    return this.http.post(this.POST_ORDER_URL, order, this.httpOptions);
  }

  /** Fetch list of delivery Option */
  fetchDeliveryOptions(): Observable<DeliveryOption[]> {
    return this.http.get<DeliveryOption[]>(this.GET_DELIV_IRL);
  }
}
