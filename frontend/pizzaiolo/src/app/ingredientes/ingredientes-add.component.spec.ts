import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientesAddComponent } from './ingredientes-add.component';

describe('IngredientesAddComponent', () => {
  let component: IngredientesAddComponent;
  let fixture: ComponentFixture<IngredientesAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngredientesAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IngredientesAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
