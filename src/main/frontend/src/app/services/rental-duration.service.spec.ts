import { TestBed } from '@angular/core/testing';

import { RentalDurationService } from './rental-duration.service';

describe('RentalDurationService', () => {
  let service: RentalDurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RentalDurationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
