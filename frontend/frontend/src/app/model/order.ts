import { Address } from "./address";

export interface Order {
  address: Address;
  // here we only need id of each book
  booksOnOrder: { id: string }[];
  firstName: string;
  lastName: string;
}
