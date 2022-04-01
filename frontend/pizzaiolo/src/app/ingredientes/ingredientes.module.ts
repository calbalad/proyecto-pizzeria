import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesComponent } from './ingredientes.component';



@NgModule({
  declarations: [
    IngredientesComponent
  ],
  exports: [
    IngredientesComponent
  ],
  imports: [
    CommonModule
  ]
})
export class IngredientesModule { }
