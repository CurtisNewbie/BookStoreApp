import { Component, OnInit } from "@angular/core";
import { HttpConnService } from "../http-conn.service";

@Component({
  selector: "app-nav-bar",
  templateUrl: "./nav-bar.component.html",
  styleUrls: ["./nav-bar.component.css"]
})
export class NavBarComponent implements OnInit {
  loggedIn: boolean;
  username: string;
  password: string;

  constructor(private httpConn: HttpConnService) {}

  ngOnInit() {}

  /** Send BASIC crendential to authentication server to retrieve JWT */
  login(name: string, password: string) {
    if (name && password) {
      let basicStr = btoa(name + ":" + password);
      this.httpConn.getJWT(basicStr);
      if (this.httpConn.hasJwt()) this.loggedIn = true;
    }
  }
}
