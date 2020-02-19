import { Book } from "./book";
import { DeliveryOption } from "./deliveryOption";

/** DTO for order that is trsferred between this app and backend */
export interface Order {
  orderId?: number;
  date?: string;
  address: {
    city: string;
    county: string;
    firstLine: string;
    secondLine: string;
    postCode: string;
  };
  booksOnOrder: {
    amount: number;
    book: Book;
  }[];
  firstName: string;
  lastName: string;
  deliveryOption: DeliveryOption;
  price?: number;
}
