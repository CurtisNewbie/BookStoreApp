/**
 * Represents a new in HomePage
 * @see HomePageComponent
 */
export class HomeNew {
  constructor(
    readonly id: number,
    public title: string,
    public content: string,
    public date: Date
  ) {}
}

/**
 * Modelling the HomeNew JSON object fetched from backend
 */
export interface BEHomeNew {
  content: string;
  date: string;
  id: number;
  title: string;
}
