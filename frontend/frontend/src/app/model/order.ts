import { Address } from "./address";

export interface Order {
  address: Address;
  // here we only need id of each book
  booksOnOrder: { amount: number; book: { id: string } }[];
  firstName: string;
  lastName: string;
  // id of each DeliveryOption object
  deliveryOption: { id: number };
}
