/**
 * Represents a Book
 */
export interface Book {
  readonly id: string;
  title: string;
  author: string;
  content: string;
  price: number;
  date?: Date;
  img?: string;
}

/**
 * Modelling the Book JSON object fetched from backend
 */
export interface BEBook {
  id: string;
  title: string;
  author: string;
  content: string;
  price: number;
  date: string;
  img: string;
}
