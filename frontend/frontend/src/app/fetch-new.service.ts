import { Injectable } from "@angular/core";
import { BEHomeNew } from "./model/home-new";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { config } from "src/environments/config";

@Injectable({
  providedIn: "root"
})
export class FetchNewService {
  /**
   * GET request URI
   */
  readonly GET_ALL_NEWS_URL: string = `http://${config.backend.hostname}:${config.backend.port}/api/new/all`;

  constructor(private http: HttpClient) {
    this.fetchNews();
  }

  /**
   * fetch a list of HomeNew from backend server
   */
  fetchNews(): Observable<BEHomeNew[]> {
    return this.http.get<BEHomeNew[]>(this.GET_ALL_NEWS_URL);
  }
}
