import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { timer } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class JWTAuthService {
  readonly JWT_URL = "http://localhost:8080/jwt/api/admin";

  /**Json Web Token */
  jwt: string;

  constructor(private http: HttpClient) {}

  /** Get JWT by sending a HTTP GET request along with BASIC authentication header */
  authenticateForJwt(basicStr: string) {
    this.http
      .get(this.JWT_URL, {
        headers: { Authorization: ["Basic " + basicStr] },
        observe: "body",
        responseType: "text"
      })
      .subscribe((jwt: string) => {
        this.jwt = jwt;
        this.createObservableTime(this.jwt);
      });
  }

  /** If the JWT has "exp" attribute, create observable timer for the JWT, and ask the admin
   * to re-authenticate when the JWT expires */
  createObservableTime(jwt: String) {
    let payload = atob(
      jwt.substring(this.jwt.indexOf(".") + 1, this.jwt.lastIndexOf("."))
    );
    let payloadObj = JSON.parse(payload);
    if (payloadObj.hasOwnProperty("exp")) {
      let gap = payloadObj.exp * 1000 - new Date().getTime();
      // create a timer for this JWT
      const source = timer(gap);
      const sub = source.subscribe(() => {
        this.jwt = null;
        alert("Your authentication token has expired, please log in again.");
      });
      alert(
        `You are successfully authenticated, your token will expire in: ${(
          gap / 60000
        ).toFixed(2)} minutes`
      );
    }
  }

  getJwt(): string {
    return this.jwt;
  }

  hasJwt(): boolean {
    if (this.jwt) return true;
    else return false;
  }

  /**
   * Create JWT string for Authorization header
   * e.g., " Bearer asdfasdfasdfasdf..."
   */
  createJwtHeaderStr(): string {
    return "Bearer " + this.getJwt();
  }
}
