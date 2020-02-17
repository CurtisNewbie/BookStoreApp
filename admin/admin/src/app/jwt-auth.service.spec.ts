import { TestBed } from "@angular/core/testing";

import { JWTAuthService } from "./jwt-auth.service";

describe("JwtAuthService", () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it("should be created", () => {
    const service: JWTAuthService = TestBed.get(JWTAuthService);
    expect(service).toBeTruthy();
  });
});
