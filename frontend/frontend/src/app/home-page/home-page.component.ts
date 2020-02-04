import { Component, OnInit } from "@angular/core";
import { HomeNew } from "../home-new";

@Component({
  selector: "app-home-page",
  templateUrl: "./home-page.component.html",
  styleUrls: ["./home-page.component.css"]
})
export class HomePageComponent implements OnInit {
  news: HomeNew[];
  constructor() {}

  ngOnInit() {
    this.fetchNews();
  }

  /*
  ----------------------------------

  To be Implemented
  
  ----------------------------------
  */
  fetchNews() {
    // dummy data
    this.news = [
      new HomeNew(
        "Sale!!!!",
        "Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!Today is a good day, we are gonna provide 100% discount, everything is for free!!!!!!! Pog!!!!",
        new Date()
      ),
      new HomeNew(
        "Welcome",
        "Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!Look around and don't buy anything!!!!",
        new Date()
      )
    ];
  }
}
