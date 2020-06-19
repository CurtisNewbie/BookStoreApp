import { Book } from './book';

/**
 * Represent an item in cart
 */
export interface CartItem {
    book: Book;
    amount: number;
}