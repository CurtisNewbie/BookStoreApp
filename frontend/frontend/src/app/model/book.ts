/**
 * Represents a Book
 */
export class Book {
  constructor(
    readonly id: string,
    public title: string,
    public author: string,
    public content: string,
    public price: number,
    public date?: Date
  ) {}
}