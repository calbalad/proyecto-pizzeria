import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PizzasComponent } from './pizzas/pizzas.component';



@NgModule({
  declarations: [
    PizzasComponent
  ],
  exports: [
    PizzasComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PizzasModule { }
