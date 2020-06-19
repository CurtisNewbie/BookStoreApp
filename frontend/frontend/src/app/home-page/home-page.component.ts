import { Component, OnInit } from "@angular/core";
import { HomeNew, BEHomeNew, toHomeNew } from "../model/home-new";
import { FetchNewService } from "../fetch-new.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-home-page",
  templateUrl: "./home-page.component.html",
  styleUrls: ["./home-page.component.css"]
})
export class HomePageComponent implements OnInit {
  news: HomeNew[] = [];
  constructor(private fetchNewService: FetchNewService) { }

  ngOnInit() {
    this.fetchNews();
  }

  private fetchNews(): void {
    this.fetchNewService.fetchNews().subscribe(
      (homeNews: BEHomeNew[]) => {
        console.log(`Fetched ${homeNews.length} news in home page`);
        for (let n of homeNews) {
          this.news.push(toHomeNew(n));
        }
      },
      (error: HttpErrorResponse) => {
        // log error msg in console
        console.error(error);
      }
    );
  }
}
