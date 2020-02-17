import { Component, OnInit } from "@angular/core";
import { Order } from "../model/order";

@Component({
  selector: "app-orders",
  templateUrl: "./orders.component.html",
  styleUrls: ["./orders.component.css"]
})
export class OrdersComponent implements OnInit {
  // demo data
  orders: Order[] = [
    {
      address: {
        city: "Sheffield",
        county: "Yorkshire",
        firstLine: "Some Randome House",
        postCode: "SH1 1HS",
        secondLine: "1st street"
      },
      booksOnOrder: [
        {
          amount: 10,
          book: {
            author: "Richard E. Williams",
            content:
              "In this book, based on his sold-out Animation Masterclass in the United States and across Europe, Williams provides the underlying principles of animation that very animator - from beginner to expert, classic animator to computer animation whiz - needs.",
            date: "2009-11-05",
            id: "1",
            img:
              "https://images-na.ssl-images-amazon.com/images/I/51YvgAKQYRL._SX421_BO1,204,203,200_.jpg",
            price: 21.95,
            title: "The Animator's Survival Kit"
          }
        },
        {
          amount: 20,
          book: {
            id: "3",
            title: "Storyboard Notebook 4:3 Panels",
            author: "Simple Storyboards",
            content:
              "By the end of the storyboarding notebook you will be able to go through your storyboard writing examples and track your progress and you will have a record of what works best for you.",
            price: 6.78,
            date: "2020-02-01",
            img:
              "https://images-na.ssl-images-amazon.com/images/I/51vJRPD1sxL._SX385_BO1,204,203,200_.jpg"
          }
        }
      ],
      date: "2020-02-13",
      deliveryOption: {
        id: 1,
        name: "Next Day Delivery (Order before 2pm)",
        price: 5
      },
      firstName: "CurtisNewbie",
      lastName: "Z",
      orderId: 8,
      price: 224.5
    }
  ];
  selectedOrder: Order;
  constructor() {}

  ngOnInit() {}

  createOrderTemplate() {
    this.selectedOrder = {
      orderId: null,
      date: null,
      address: {
        city: "",
        county: "",
        firstLine: "",
        postCode: "",
        secondLine: ""
      },
      booksOnOrder: null,
      deliveryOption: {
        id: 3,
        name: "One Week Delivery",
        price: 2.6
      },
      firstName: "",
      lastName: "",
      price: null
    };
  }

  selectOrder(index: number) {
    this.selectedOrder = this.orders[index];
  }

  /** Today in YYYY-DD-MM */
  today(): string {
    let d = new Date();
    return d.getUTCFullYear() + "-" + d.getUTCMonth() + "-" + d.getUTCDay();
  }

  updateOrder(order: Order) {}

  createOrder(order: Order) {}

  deleteOrder(order: Order) {}

  /** Find book's title by its id */
  findBookTitle(id: number) {
    // demo data
    return "The Animator's Survival Kit";
  }
}
