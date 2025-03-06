import { TestBed } from '@angular/core/testing';

import { RentaldurationService } from './rentalduration.service';

describe('RentaldurationService', () => {
  let service: RentaldurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RentaldurationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
