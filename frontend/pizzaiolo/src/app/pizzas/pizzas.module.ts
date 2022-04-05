import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PizzasComponent } from './pizzas.component';
import {OrderListModule} from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {FormsModule} from '@angular/forms';
import {CardModule} from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import {PickListModule} from 'primeng/picklist';
import {InputNumberModule} from 'primeng/inputnumber';
import {ToastModule} from 'primeng/toast';
import {InputTextModule} from 'primeng/inputtext';


@NgModule({
  declarations: [
    PizzasComponent
  ],
  exports: [
    PizzasComponent, OrderListModule, ButtonModule,
  ],
  imports: [
    CommonModule, OrderListModule, ButtonModule, RouterModule,
    ToolbarModule, TableModule, DialogModule, FormsModule, CardModule,
    DropdownModule, PickListModule, InputNumberModule, ToastModule,
    InputTextModule
  ]
})
export class PizzasModule { }
