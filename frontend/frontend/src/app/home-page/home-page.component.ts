import { Component, OnInit } from "@angular/core";
import { HomeNew } from "../model/home-new";
import { FetchNewService } from "../fetch-new.service";

@Component({
  selector: "app-home-page",
  templateUrl: "./home-page.component.html",
  styleUrls: ["./home-page.component.css"]
})
export class HomePageComponent implements OnInit {
  news: HomeNew[];
  constructor(private fetchNewService: FetchNewService) {}

  ngOnInit() {
    this.fetchNews();
  }

  fetchNews() {
    this.news = this.fetchNewService.getNews();
  }
}
