import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { HomeNew } from "./model/homeNew";
import { Observable } from "rxjs";
import { JWTAuthService } from "./jwt-auth.service";

@Injectable({
  providedIn: "root"
})
export class NewsService {
  readonly GET_ALL_NEWS = "http://localhost:8080/api/new/all";
  readonly SINGLE_NEW_URL = "http://localhost:8080/api/new";

  constructor(private http: HttpClient, private jwtAuth: JWTAuthService) {}

  fetchAllNews(): Observable<HomeNew[]> {
    return this.http.get<HomeNew[]>(this.GET_ALL_NEWS);
  }

  fetchNewById(id: string): Observable<HomeNew> {
    return this.http.get<HomeNew>(this.SINGLE_NEW_URL + `?id=${id}`);
  }

  /*
  ---------------------------------------
  
  not yet implemented in backend server
  
  ---------------------------------------
  */
  fetchNewByTitle(title: string): Observable<HomeNew> {
    return null;
  }

  updateNew(homeNew: HomeNew): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.put(this.SINGLE_NEW_URL, homeNew, options);
  }

  createNew(homeNew: HomeNew): Observable<any> {
    let options: { headers: HttpHeaders; observe } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response"
    };
    return this.http.post(this.SINGLE_NEW_URL, homeNew, options);
  }

  deleteNew(homeNewId: number): Observable<any> {
    let options: { headers: HttpHeaders; observe; responseType } = {
      headers: new HttpHeaders({
        Authorization: this.jwtAuth.createJwtHeaderStr()
      }),
      observe: "response",
      responseType: "text"
    };
    return this.http.delete(this.SINGLE_NEW_URL + `?id=${homeNewId}`, options);
  }
}
