import { TestBed } from "@angular/core/testing";

import { OrderReviewService } from "./order-review.service";

describe("OrderReviewService", () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it("should be created", () => {
    const service: OrderReviewService = TestBed.get(OrderReviewService);
    expect(service).toBeTruthy();
  });
});
