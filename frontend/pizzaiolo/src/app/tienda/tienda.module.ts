import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CocinaComponent, RepartoComponent } from './tienda.component';
import { OrderListModule } from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';

import { RouterModule } from '@angular/router';
import {ToolbarModule} from 'primeng/toolbar';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
import {CardModule} from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessagesModule } from 'primeng/messages';



@NgModule({
  declarations: [
    CocinaComponent, RepartoComponent,

  ],
  imports: [
    CommonModule, OrderListModule, ButtonModule, RouterModule, ToolbarModule, TableModule, DialogModule, FormsModule, CardModule,DropdownModule,
    ConfirmDialogModule, MessagesModule

  ]
})
export class TiendaModule { }
