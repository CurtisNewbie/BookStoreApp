import { Injectable } from "@angular/core";
import { HomeNew } from "./model/home-new";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class FetchNewService {
  /**
   * GET request URI
   */
  readonly GET_ALL_NEWS_URL: string = "http://localhost:8080/api/new/all";
  /**
   * list of HomeNew objects
   */
  private news: HomeNew[] = [];

  constructor(private http: HttpClient) {
    this.fetchNews();
  }

  /**
   * fetch a list of HomeNew from backend server
   */
  fetchNews() {
    console.log("[fetch-new.service] - fetching news...");
    let obs = this.http.get<BEHomeNew[]>(this.GET_ALL_NEWS_URL);
    obs.subscribe(homeNews => {
      for (let o of homeNews) {
        let hn = new HomeNew(
          o.id,
          o.title,
          o.content,
          new Date(
            parseInt(o.date.substring(0, 4)),
            parseInt(o.date.substring(5, 7)),
            parseInt(o.date.substring(8))
          )
        );
        this.news.push(hn);
      }
      console.log(homeNews);
    });
  }

  /**
   * Get a list of news (shallow copy)
   */
  getNews(): HomeNew[] {
    return this.news;
  }
}

/**
 * Modelling the JSON object fetched from backend
 */
interface BEHomeNew {
  content: string;
  date: string;
  id: number;
  title: string;
}
