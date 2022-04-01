import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesComponent } from './ingredientes.component';
import {OrderListModule} from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [
    IngredientesComponent
  ],
  exports: [
    IngredientesComponent, OrderListModule, ButtonModule,

  ],
  imports: [
    CommonModule, OrderListModule, ButtonModule,

  ]
})
export class IngredientesModule { }
