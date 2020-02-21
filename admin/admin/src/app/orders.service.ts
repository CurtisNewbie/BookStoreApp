import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { JWTAuthService } from "./jwt-auth.service";
import { Observable } from "rxjs";
import { Order } from "./model/order";

@Injectable({
  providedIn: "root"
})
export class OrdersService {
  readonly GET_ALL_ORDER = "http://localhost:8080/api/order/all";
  readonly SINGLE_ORDER_URL = "http://localhost:8080/api/order";

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
