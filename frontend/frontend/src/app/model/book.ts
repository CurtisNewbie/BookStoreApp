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
