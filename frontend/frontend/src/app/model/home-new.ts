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
  ) { }
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

/**
 * Convert BEHomeNew object to HomeNew 
 * 
 * @param n BEHomeNew object
 */
export function toHomeNew(n: BEHomeNew): HomeNew {
  return new HomeNew(
    n.id,
    n.title,
    n.content,
    new Date(
      parseInt(n.date.substring(0, 4)),
      parseInt(n.date.substring(5, 7)),
      parseInt(n.date.substring(8))
    )
  )
}
