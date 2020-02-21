import { TestBed } from '@angular/core/testing';

import { DeliveryOptionsService } from './delivery-options.service';

describe('DeliveryOptionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryOptionsService = TestBed.get(DeliveryOptionsService);
    expect(service).toBeTruthy();
  });
});
