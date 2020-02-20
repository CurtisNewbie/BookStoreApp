/**
 * Represents a Book
 */
export interface Book {
  readonly id?: number;
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
  id: number;
  title: string;
  author: string;
  content: string;
  price: number;
  date: string;
  img: string;
}
