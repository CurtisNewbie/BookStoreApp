import { Injectable } from "@angular/core";
import { HomeNew } from "./model/home-new";

@Injectable({
  providedIn: "root"
})
export class FetchNewService {
  // dummy data
  readonly NEWS_DEMO = [
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

  private news: HomeNew[];

  constructor() {
    this.fetchNews();
  }

  fetchNews() {
    console.log("FetchNew Service...fetching...");
    this.news = this.NEWS_DEMO;
  }

  /**
   * Get a list of news (shallow copy)
   */
  getNews(): HomeNew[] {
    return this.news;
  }
}
