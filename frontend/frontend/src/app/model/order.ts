import { Address } from "./address";
import { BEBook } from "./book";
import { DeliveryOption } from "./deliveryOption";

export interface Order {
  address: Address;
  // here we only need id of each book
  booksOnOrder: { amount: number; book: { id: string } }[];
  firstName: string;
  lastName: string;
  // id of each DeliveryOption object
  deliveryOption: { id: number };
}

/** Model the Order JSON object received from Backend */
export interface BEOrder extends Order {
  // override
  booksOnOrder: {
    amount: number;
    book: BEBook;
  }[];
  deliveryOption: DeliveryOption;
  // extra
  orderId: number;
  date: string;
  price: number;
}
