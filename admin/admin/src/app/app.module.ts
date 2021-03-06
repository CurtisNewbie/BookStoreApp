import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { NewsComponent } from './news/news.component';
import { BooksComponent } from './books/books.component';
import { OrdersComponent } from './orders/orders.component';
import { DeliveryOptionComponent } from './delivery-option/delivery-option.component';

@NgModule({
  declarations: [AppComponent, NavBarComponent, NewsComponent, BooksComponent, OrdersComponent, DeliveryOptionComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
