import { TestBed } from '@angular/core/testing';

import { HttpConnService } from './http-conn.service';

describe('HttpConnService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpConnService = TestBed.get(HttpConnService);
    expect(service).toBeTruthy();
  });
});
