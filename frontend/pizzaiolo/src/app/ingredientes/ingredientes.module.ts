import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesComponent } from './ingredientes.component';
import {OrderListModule} from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';
import { IngredientesAddComponent } from './ingredientes-add.component';


@NgModule({
  declarations: [
    IngredientesComponent,
    IngredientesAddComponent
  ],
  exports: [
    IngredientesComponent, OrderListModule, ButtonModule, IngredientesAddComponent

  ],
  imports: [
    CommonModule, OrderListModule, ButtonModule,

  ]
})
export class IngredientesModule { }
