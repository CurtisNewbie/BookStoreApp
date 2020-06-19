import { Component, OnInit } from "@angular/core";
import { Book, BEBook, toBook } from "../model/book";
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
  ) { }

  ngOnInit() {
    // set up this.book
    this.fetchBook();
  }

  /** 
   * Fetch book from backend based on the query parameter "id" in URL, 
   * the book that is fetched is set to the "book" field 
   */
  private fetchBook(): void {
    const id: string = this.route.snapshot.queryParamMap.get("id");
    if (id !== null) {
      this.fetchBookService.fetchBookById(id).subscribe((b: BEBook) => {
        console.log("Fetched book id: ", b.id);
        this.book = toBook(b);
      });
    }
  }

  /**
   * Add this book to cart
   */
  addToCart(): void {
    this.checkoutService.addToCart(this.book);
  }
}
