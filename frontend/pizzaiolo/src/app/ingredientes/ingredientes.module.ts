import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesComponent } from './ingredientes.component';
import {OrderListModule} from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';

import { RouterModule } from '@angular/router';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {FormsModule} from '@angular/forms';
import {CardModule} from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import {InputTextModule} from 'primeng/inputtext';
import {InputNumberModule} from 'primeng/inputnumber';

@NgModule({
  declarations: [
    IngredientesComponent,

  ],
  exports: [
    IngredientesComponent, OrderListModule, ButtonModule,

  ],
  imports: [
    CommonModule, OrderListModule, ButtonModule, RouterModule, ToolbarModule, TableModule, DialogModule, FormsModule, CardModule,DropdownModule, InputTextModule, InputNumberModule

  ]
})
export class IngredientesModule { }
