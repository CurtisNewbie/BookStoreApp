import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class JWTAuthService {
  readonly JWT_URL = "http://localhost:8080/jwt/api/admin";

  /**Json Web Token */
  jwt: string;

  constructor(private http: HttpClient) {}

  /** Get JWT by sending a HTTP GET request along with BASIC authentication header */
  getJWT(basicStr: string) {
    this.http
      .get(this.JWT_URL, {
        headers: { Authorization: ["Basic " + basicStr] },
        observe: "body",
        responseType: "text"
      })
      .subscribe((jwt: string) => {
        this.jwt = jwt;
        console.log(jwt);
      });
  }

  getJwt(): string {
    return this.jwt;
  }

  hasJwt(): boolean {
    if (this.jwt) return true;
    else return false;
  }
}
