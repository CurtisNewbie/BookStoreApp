import { Component, OnInit } from "@angular/core";
import { Book, BEBook } from "../model/book";
import { CheckoutService } from "../checkout.service";
import { FetchBookService } from "../fetch-book.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-book-detail",
  templateUrl: "./book-detail.component.html",
  styleUrls: ["./book-detail.component.css"]
})
export class BookDetailComponent implements OnInit {
  book: Book;
  constructor(
    private checkoutService: CheckoutService,
    private fetchBookService: FetchBookService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.fetchBook();
  }

  /** Fetch book from backend based on the query parameter "id" in URL */
  fetchBook() {
    const id: string = this.route.snapshot.queryParamMap.get("id");
    if (id !== null) {
      this.fetchBookService.fetchBookById(id).subscribe((b: BEBook) => {
        this.book = {
          id: b.id,
          title: b.title,
          author: b.author,
          content: b.content,
          price: b.price,
          date:
            b.date !== undefined
              ? new Date(
                  parseInt(b.date.substring(0, 4)),
                  parseInt(b.date.substring(5, 7)) - 1,
                  parseInt(b.date.substring(8, 10))
                )
              : null,
          img: b.img !== undefined ? b.img : null
        };
      });
    }
  }

  addToCart() {
    this.checkoutService.addToCart(this.book);
  }
}
