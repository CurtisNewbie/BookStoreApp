import { Component, OnInit } from "@angular/core";
import { HomeNew } from "../model/homeNew";
import { JWTAuthService } from "../jwt-auth.service";
import { NewsService } from "../news.service";
import { HttpResponse } from "@angular/common/http";
import { Refreshable } from "../refreshable";

@Component({
  selector: "app-news",
  templateUrl: "./news.component.html",
  styleUrls: ["./news.component.css"]
})
export class NewsComponent implements OnInit, Refreshable {
  news: HomeNew[];
  selectedNew: HomeNew;

  constructor(
    private jwtAuth: JWTAuthService,
    private newsService: NewsService
  ) {}

  ngOnInit() {
    this.getAllNews();
    this.jwtAuth.registerCurrPage(this);
  }

  getAllNews() {
    this.newsService.fetchAllNews().subscribe((allNews: HomeNew[]) => {
      this.news = allNews;
    });
  }

  /** Select one that is displayed */
  selectNew(homeNew: HomeNew) {
    this.selectedNew = Object.assign({}, homeNew);
  }

  updateNew(homeNew: HomeNew) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.newsService.updateNew(homeNew).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200) console.log(`Failed to update "${homeNew.id}"`);
        else console.log(`Successfully updated "${homeNew.id}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  deleteNew(homeNew: HomeNew) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.newsService.deleteNew(homeNew.id).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200) console.log(`Failed to delete "${homeNew.id}"`);
        else console.log(`"${resp.body}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  createNew(homeNew: HomeNew) {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    /** id is created in backend server */
    let tempDTO: HomeNew = {
      content: homeNew.content,
      date: homeNew.date,
      title: homeNew.title
    };
    this.newsService.createNew(tempDTO).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 201)
          console.log(`Failed to create "${homeNew.title}"`);
        else console.log(`Successfully created "${homeNew.title}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  /**
   * Create temporary template
   */
  createTemplate() {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }

    this.selectedNew = {
      content: "",
      date: "",
      id: null,
      title: ""
    };
  }

  /**
   * Clear previous storage of HomeNew[], retrieve these data again from backend server.
   */
  refresh() {
    this.news = null;
    this.selectedNew = null;
    this.getAllNews();
  }
}
