import { TestBed } from '@angular/core/testing';

import { FetchNewService } from './fetch-new.service';

describe('FetchNewService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchNewService = TestBed.get(FetchNewService);
    expect(service).toBeTruthy();
  });
});
