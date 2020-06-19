import { Address } from "./address";
import { BEBook, BookInOrder } from "./book";
import { DeliveryOption } from "./deliveryOption";

export interface Order {
  address: Address;
  // here we only need id of each book
  books: BookInOrder[];
  firstName: string;
  lastName: string;
  // id of each DeliveryOption object
  deliveryOptionId: number;
}

/** Model the Order JSON object received from Backend */
export interface BEOrder {
  address: Address;
  books: {
    amount: number;
    book: BEBook;
  }[];
  firstName: string;
  lastName: string;
  deliveryOption: DeliveryOption;
  orderId: number;
  date: string;
  price: number;
}


