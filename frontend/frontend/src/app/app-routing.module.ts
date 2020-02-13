import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomePageComponent } from "./home-page/home-page.component";
import { BookListComponent } from "./book-list/book-list.component";
import { BookDetailComponent } from "./book-detail/book-detail.component";
import { CheckoutComponent } from "./checkout/checkout.component";
import { OrderConfirmComponent } from "./order-confirm/order-confirm.component";
import { OrderSentComponent } from "./order-sent/order-sent.component";

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "home", component: HomePageComponent },
  { path: "list", component: BookListComponent },
  // for detail,  query param : "id"
  { path: "detail", component: BookDetailComponent },
  { path: "checkout", component: CheckoutComponent },
  { path: "order", component: OrderConfirmComponent },
  { path: "order/sent", component: OrderSentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
