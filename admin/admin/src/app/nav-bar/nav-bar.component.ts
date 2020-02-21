import { Component, OnInit } from "@angular/core";
import { JWTAuthService } from "../jwt-auth.service";

@Component({
  selector: "app-nav-bar",
  templateUrl: "./nav-bar.component.html",
  styleUrls: ["./nav-bar.component.css"]
})
export class NavBarComponent implements OnInit {
  username: string;
  password: string;

  constructor(private jwtAuth: JWTAuthService) {}

  ngOnInit() {}

  /** Send BASIC crendential to authentication server to retrieve JWT */
  login(name: string, password: string) {
    if (name && password) {
      let basicStr = btoa(name + ":" + password);
      this.jwtAuth.authenticateForJwt(basicStr);
      this.username = "";
      this.password = "";
    }
  }

  hasAuth(): boolean {
    return this.jwtAuth.hasJwt();
  }
}
