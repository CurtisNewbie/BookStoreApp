import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from "./app.component";
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { HomePageComponent } from "./home-page/home-page.component";
import { BookListComponent } from "./book-list/book-list.component";
import { BookDetailComponent } from "./book-detail/book-detail.component";
import { CheckoutComponent } from "./checkout/checkout.component";
import { AppRoutingModule } from "./app-routing.module";
import { OrderConfirmComponent } from "./order-confirm/order-confirm.component";

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomePageComponent,
    BookListComponent,
    BookDetailComponent,
    CheckoutComponent,
    OrderConfirmComponent
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
