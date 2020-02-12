import { Component, OnInit } from "@angular/core";
import { HomeNew, BEHomeNew } from "../model/home-new";
import { FetchNewService } from "../fetch-new.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-home-page",
  templateUrl: "./home-page.component.html",
  styleUrls: ["./home-page.component.css"]
})
export class HomePageComponent implements OnInit {
  news: HomeNew[] = [];
  constructor(private fetchNewService: FetchNewService) {}

  ngOnInit() {
    this.fetchNews();
  }

  fetchNews() {
    this.fetchNewService.fetchNews().subscribe(
      (homeNews: BEHomeNew[]) => {
        console.log(`Fetched ${homeNews.length} news in home page`);
        for (let o of homeNews) {
          this.news.push(
            new HomeNew(
              o.id,
              o.title,
              o.content,
              new Date(
                parseInt(o.date.substring(0, 4)),
                parseInt(o.date.substring(5, 7)),
                parseInt(o.date.substring(8))
              )
            )
          );
        }
      },
      (error: HttpErrorResponse) => {
        // log error msg in console
        console.error(error);
      }
    );
  }
}
