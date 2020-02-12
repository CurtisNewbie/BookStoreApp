import { Injectable } from "@angular/core";
import { BEHomeNew } from "./model/home-new";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class FetchNewService {
  /**
   * GET request URI
   */
  readonly GET_ALL_NEWS_URL: string = "http://localhost:8080/api/new/all";

  constructor(private http: HttpClient) {
    this.fetchNews();
  }

  /**
   * fetch a list of HomeNew from backend server
   */
  fetchNews(): Observable<BEHomeNew[]> {
    console.log("[fetch-new.service] - fetching news...");
    return this.http.get<BEHomeNew[]>(this.GET_ALL_NEWS_URL);
  }
}
