import { TestBed } from '@angular/core/testing';

import { FetchBookService } from './fetch-book.service';

describe('FetchBookService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchBookService = TestBed.get(FetchBookService);
    expect(service).toBeTruthy();
  });
});
