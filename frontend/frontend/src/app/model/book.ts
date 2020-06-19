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

export function toBook(b: BEBook): Book {
  return {
    id: b.id,
    title: b.title,
    author: b.author,
    content: b.content,
    price: b.price,
    date:
      b.date !== undefined
        ? new Date(
          parseInt(b.date.substring(0, 4)),
          parseInt(b.date.substring(5, 7)) - 1,
          parseInt(b.date.substring(8, 10))
        )
        : null,
    img: b.img !== undefined ? b.img : null
  };
}