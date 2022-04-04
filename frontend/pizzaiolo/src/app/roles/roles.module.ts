import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RolesComponent } from './roles.component';
import {OrderListModule} from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';

import { RouterModule } from '@angular/router';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {FormsModule} from '@angular/forms';
import {CardModule} from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
  declarations: [
    RolesComponent,

  ],
  exports: [
    RolesComponent, OrderListModule, ButtonModule,

  ],
  imports: [
    CommonModule, OrderListModule, ButtonModule, RouterModule, ToolbarModule, TableModule, DialogModule, FormsModule, CardModule,DropdownModule

  ]
})
export class RolesModule { }
