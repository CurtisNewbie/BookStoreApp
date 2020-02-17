import { Component, OnInit } from "@angular/core";
import { HomeNew } from "../model/homeNew";
import { JWTAuthService } from "../jwt-auth.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-news",
  templateUrl: "./news.component.html",
  styleUrls: ["./news.component.css"]
})
export class NewsComponent implements OnInit {
  // demo data
  news: HomeNew[] = [
    {
      content: "We are on sale",
      date: "2019-12-12",
      id: 1,
      title: "Breaking New"
    },
    {
      content: "We are the best bookstore ever!",
      date: "2019-11-12",
      id: 2,
      title: "Lets Celebrate!!!"
    },
    {
      content: "It's okay if you don't like this bookstore.",
      date: "2019-10-12",
      id: 3,
      title: "Confession"
    }
  ];
  selectedNew: HomeNew;

  constructor(private jwtAuth: JWTAuthService, private router: Router) {}

  ngOnInit() {
    // if (!this.hasJwt()) {
    //   alert("Login first to authenticate your operations.");
    //   this.router.navigateByUrl("/");
    // } else {
    //   // retrive data
    // }
  }

  hasJwt(): boolean {
    return this.jwtAuth.hasJwt();
  }

  selectNew(index: number) {
    this.selectedNew = this.news[index];
  }

  updateNew(homeNew: HomeNew) {
    // PUT request
  }

  deleteNew(homeNew: HomeNew) {
    // DELETE request
  }

  createNew(homeNew: HomeNew) {
    // POST request
  }

  /**
   * Create temporary template
   */
  createTemplate() {
    this.selectedNew = {
      content: "",
      date: "",
      id: null,
      title: ""
    };
  }
}
