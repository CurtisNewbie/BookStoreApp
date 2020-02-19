/** DTO for book that is trsferred between this app and backend */
export interface Book {
  id: string;
  title?: string;
  author?: string;
  content?: string;
  price?: number;
  date?: string;
  img?: string;
}
