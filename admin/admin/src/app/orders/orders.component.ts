import { Component, OnInit } from "@angular/core";
import { Order } from "../model/order";
import { OrdersService } from "../orders.service";
import { JWTAuthService } from "../jwt-auth.service";
import { HttpResponse } from "@angular/common/http";
import { BooksService } from "../books.service";
import { Book } from "../model/book";
import { DeliveryOption } from "../model/deliveryOption";
import { DeliveryOptionsService } from "../delivery-options.service";
import { Refreshable } from "../refreshable";

@Component({
  selector: "app-orders",
  templateUrl: "./orders.component.html",
  styleUrls: ["./orders.component.css"]
})
export class OrdersComponent implements OnInit, Refreshable {
  /** constant that indicates the book with current id is not found in backend */
  readonly BOOK_NOT_FOUND = "Not Found";

  /** a list of delivery options */
  delivOpts: DeliveryOption[];
  /** All the orders */
  orders: Order[];
  /** The order that is selected and displayed */
  selectedOrder: Order;
  /** temporary book that may be added to the order (only the id of the book and its amount are truely needed) */
  tempBook: { book: Book; amount: number };

  constructor(
    private jwtAuth: JWTAuthService,
    private ordersService: OrdersService,
    private booksService: BooksService,
    private delivOptsService: DeliveryOptionsService
  ) {}

  ngOnInit() {
    this.getDeliveryOptions();
    this.getAllOrders();
    this.tempBook = this.createEmptyTempBook();
    this.jwtAuth.registerCurrPage(this);
  }

  /** Add a book to the temprary order that is about to be created or updated */
  addTempBookToTempOrder(): void {
    // validate before add to selectedOrder
    if (!this.tempBook.book.id) {
      alert("Book Id invalid.");
    } else if (this.tempBook.amount <= 0)
      alert(
        "You must have at least one book in order to add it in your order."
      );
    else if (this.tempBook.book.title === this.BOOK_NOT_FOUND)
      alert(
        "Your book is not found in backend. Please make sure the book id is correct."
      );
    else {
      // add to selectedOrder
      this.selectedOrder.booksOnOrder.push(this.tempBook);
      this.tempBook = this.createEmptyTempBook();
    }
  }

  /** Create empty order template for creating a new Order */
  createEmptyTempOrder() {
    if (!this.jwtAuth.hasJwt()) alert("Login First!");
    else
      this.selectedOrder = {
        orderId: null,
        address: {
          city: "",
          county: "",
          firstLine: "",
          postCode: "",
          secondLine: ""
        },
        booksOnOrder: [],
        deliveryOption: {
          id: null,
          name: "",
          price: null
        },
        firstName: "",
        lastName: "",
        price: null
      };
  }

  /** Select one that is displayed */
  selectOrder(order: Order) {
    this.selectedOrder = Object.assign({}, order);
  }

  /** Today in YYYY-DD-MM */
  today(): string {
    let d = new Date();
    let month = d.getUTCMonth();
    let day = d.getUTCDay();
    // append 0 if necessary
    let monthStr = month < 10 ? "0" + month : month;
    let dayStr = day < 10 ? "0" + day : day;
    return d.getUTCFullYear() + "-" + monthStr + "-" + dayStr;
  }

  updateOrder(order: Order): void {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }
    console.log(order);
    this.ordersService.updateOrder(order).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200)
          console.log(`Failed to update "${order.orderId}"`);
        else console.log(`Successfully updated "${order.orderId}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  createOrder(order: Order): void {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }
    let tempDTO: Order = this.convertToOrderDTO(order);
    this.ordersService.createOrder(tempDTO).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200)
          console.log("Failed to create this order", resp.status);
        else console.log("Successfully created this order");
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  deleteOrder(order: Order): void {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }
    this.ordersService.deleteOrderById(order.orderId).subscribe({
      next: (resp: HttpResponse<any>) => {
        if (resp.status != 200)
          console.log(`Failed to delete "${order.orderId}"`);
        else console.log(`"${resp.body}"`);
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.refresh();
      }
    });
  }

  getAllOrders() {
    if (!this.jwtAuth.hasJwt()) {
      alert("Login First!");
      return;
    }
    this.ordersService.fetchAllOrders().subscribe((orders: Order[]) => {
      this.orders = orders;
    });
  }

  getDeliveryOptions(): void {
    this.delivOptsService.fetchAllDeliveryOpt().subscribe(val => {
      this.delivOpts = val;
    });
  }

  /**
   * When the id of the temporary book is changed, this method updates its title.
   * I.e, this method displays the temporary book's title on the fly.
   *
   * @param bookId id of the temporary book
   */
  updateTempBookTitle(bookId: string): void {
    if (bookId)
      this.booksService.fetchBookById(parseInt(bookId)).subscribe({
        next: (val: Book) => {
          this.tempBook.book.title = val.title;
        },
        error: () => {
          this.tempBook.book.title = this.BOOK_NOT_FOUND;
        }
      });
  }

  /**
   * Clear previous storage of orders, retrieve these data again from backend server.
   */
  refresh() {
    this.orders = null;
    this.selectedOrder = null;
    this.getAllOrders();
  }

  /** Create empty temporary book that may or may not be added to the order */
  createEmptyTempBook(): { amount: number; book: Book } {
    return {
      book: {
        id: null,
        title: ""
      },
      amount: 0
    };
  }

  /** Convert to DTO, removes unnecessary data */
  convertToOrderDTO(order: Order): Order {
    let list: { amount; book }[] = [];
    for (let b of order.booksOnOrder) {
      list.push({
        amount: b.amount,
        book: {
          id: b.book.id
        }
      });
    }
    return {
      address: order.address,
      booksOnOrder: list,
      deliveryOption: { id: order.deliveryOption.id },
      firstName: order.firstName,
      lastName: order.lastName
    };
  }

  /**
   * Select one of the radio button for delivery option
   *
   * @param n index of the selected delivery option
   */
  selectRadioButton(index: number): void {
    if (index >= 0 && index < this.delivOpts.length)
      this.selectedOrder.deliveryOption = this.delivOpts[index];
  }

  removeBookFromSelectedOrder(index: number): void {
    this.selectedOrder.booksOnOrder.splice(index, 1);
  }
}
