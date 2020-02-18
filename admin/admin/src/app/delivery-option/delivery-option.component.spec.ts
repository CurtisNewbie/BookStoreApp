import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryOptionComponent } from './delivery-option.component';

describe('DeliveryOptionComponent', () => {
  let component: DeliveryOptionComponent;
  let fixture: ComponentFixture<DeliveryOptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryOptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryOptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
