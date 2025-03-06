import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveRentsComponent } from './active-rents.component';

describe('ActiveRentsComponent', () => {
  let component: ActiveRentsComponent;
  let fixture: ComponentFixture<ActiveRentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActiveRentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveRentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
