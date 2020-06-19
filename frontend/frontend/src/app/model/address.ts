export interface Address {
  firstLine: string;
  secondLine: string;
  city: string;
  county: string;
  postCode: string;
}

/**
 * Return an empty Address
 */
export function emptyAddress(): Address {
  return {
    city: "",
    county: "",
    firstLine: "",
    secondLine: "",
    postCode: ""
  };
}