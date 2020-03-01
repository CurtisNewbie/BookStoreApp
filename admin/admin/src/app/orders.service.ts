import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { JWTAuthService } from "./jwt-auth.service";
import { Observable } from "rxjs";
import { Order } from "./model/order";
import { config } from "src/environments/config";

@Injectable({
  providedIn: "root"
})
export class OrdersService {
  readonly GET_ALL_ORDER = `http://${config.backend.hostname}:${config.backend.port}/api/order/all`;
  readonly SINGLE_ORDER_URL = `http://${config.backend.hostname}:${config.backend.port}/api/order`;

  constructor(private http: HttpClient, private jwtAuth: JWTAuthService) {}

  fetchAllOrders(): Observable<Order[]> {
    let options: { headers: HttpHeaders } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      })
    };
    return this.http.get<Order[]>(this.GET_ALL_ORDER, options);
  }

  fetchOrderById(id: string): Observable<Order> {
    return this.http.get<Order>(this.SINGLE_ORDER_URL + `?id=${id}`);
  }

  updateOrder(order: Order): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.put(this.SINGLE_ORDER_URL, order, options);
  }

  createOrder(order: Order): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.post(this.SINGLE_ORDER_URL, order, options);
  }

  deleteOrderById(orderId: number): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.delete(this.SINGLE_ORDER_URL + `?id=${orderId}`, options);
  }
}
