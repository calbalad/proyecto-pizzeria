import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesModule } from '../ingredientes/ingredientes.module';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesComponent } from '../ingredientes/ingredientes.component';
import { PizzasComponent } from '../pizzas/pizzas.component';
import { PizzasModule } from '../pizzas';


import {OrderListModule} from 'primeng/orderlist';
import { ManagerComponent } from './manager.component';




const routes: Routes = [
  { path: '', component: ManagerComponent },
  { path: 'ingredientes', children: [
    { path: '', component: IngredientesComponent },

  ]},
  { path: 'pizzas', children: [
     { path: '', component: PizzasComponent },
    // { path: ':id/edit', component: BlogEditComponent },
    // { path: ':id', component: BlogViewComponent },
    // { path: ':id/:kk', component: BlogViewComponent },
  ]},
]

@NgModule({
  declarations: [
    ManagerComponent
  ],
  exports: [OrderListModule],
  imports: [
    CommonModule, IngredientesModule, PizzasModule, RouterModule.forChild(routes), OrderListModule,
  ]
})
export class ManagerModule { }
