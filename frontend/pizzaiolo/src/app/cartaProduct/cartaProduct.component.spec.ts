import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartaProductComponent } from './cartaProduct.component';

describe('CartaProductComponent', () => {
  let component: CartaProductComponent;
  let fixture: ComponentFixture<CartaProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartaProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartaProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
