import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { JWTAuthService } from "./jwt-auth.service";
import { DeliveryOption } from "./model/deliveryOption";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class DeliveryOptionsService {
  readonly GET_ALL_OPTIONS = "http://localhost:8080/api/delivery/option/all";
  readonly SINGLE_OPT_URL = "http://localhost:8080/api/delivery/option";

  constructor(private http: HttpClient, private jwtAuth: JWTAuthService) {}

  fetchAllDeliveryOpt(): Observable<DeliveryOption[]> {
    return this.http.get<DeliveryOption[]>(this.GET_ALL_OPTIONS);
  }

  fetchDeliveryOptById(id: string): Observable<DeliveryOption> {
    return this.http.get<DeliveryOption>(this.SINGLE_OPT_URL + `?id=${id}`);
  }

  updateDeliveryOpt(deliveryOpt: DeliveryOption): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.put(this.SINGLE_OPT_URL, deliveryOpt, options);
  }

  createDeliveryOpt(deliveryOpt: DeliveryOption): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.post(this.SINGLE_OPT_URL, deliveryOpt, options);
  }

  deleteDeliveryOpt(deliveryOptId: number): Observable<any> {
    let options: { headers: HttpHeaders; observe; responseType } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response",
      responseType: "text"
    };
    return this.http.delete(
      this.SINGLE_OPT_URL + `?id=${deliveryOptId}`,
      options
    );
  }
}
