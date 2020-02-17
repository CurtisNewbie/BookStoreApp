import { Component, OnInit } from "@angular/core";
import { Book } from "../model/book";

@Component({
  selector: "app-books",
  templateUrl: "./books.component.html",
  styleUrls: ["./books.component.css"]
})
export class BooksComponent implements OnInit {
  // demo data
  books: Book[] = [
    {
      id: "1",
      title: "The Animator's Survival Kit",
      author: "Richard E. Williams",
      content:
        "In this book, based on his sold-out Animation Masterclass in the United States and across Europe, Williams provides the underlying principles of animation that very animator - from beginner to expert, classic animator to computer animation whiz - needs.",
      price: 21.95,
      date: "2009-11-05",
      img:
        "https://images-na.ssl-images-amazon.com/images/I/51YvgAKQYRL._SX421_BO1,204,203,200_.jpg"
    },
    {
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
  ];
  selectedBook: Book;
  constructor() {}

  ngOnInit() {}

  createBookTemplate() {
    this.selectedBook = {
      author: "",
      content: "",
      date: "",
      id: null,
      img: "",
      price: null,
      title: ""
    };
  }

  selectBook(index: number) {
    this.selectedBook = this.books[index];
  }

  updateBook(book: Book) {
    // PUT request
  }

  createBook(book: Book) {
    // POST request
  }

  deleteBook(book: Book) {
    // DELETE request
  }
}
