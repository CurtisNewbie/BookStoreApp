import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { OrderSentComponent } from "./order-sent.component";

describe("OrderSentComponent", () => {
  let component: OrderSentComponent;
  let fixture: ComponentFixture<OrderSentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OrderSentComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderSentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
