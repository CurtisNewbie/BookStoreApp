import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { HomePageComponent } from "./home-page/home-page.component";

@NgModule({
  declarations: [AppComponent, NavBarComponent, HomePageComponent],
  imports: [BrowserModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
