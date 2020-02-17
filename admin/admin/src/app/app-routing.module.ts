import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { NewsComponent } from "./news/news.component";
import { BooksComponent } from "./books/books.component";
import { OrdersComponent } from "./orders/orders.component";

const routes: Routes = [
  { path: "news", component: NewsComponent },
  { path: "books", component: BooksComponent },
  { path: "orders", component: OrdersComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
