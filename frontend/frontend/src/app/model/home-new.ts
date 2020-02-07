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
