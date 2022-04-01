import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesModule } from '../ingredientes/ingredientes.module';
import { Routes } from '@angular/router';
import { IngredientesComponent } from '../ingredientes/ingredientes.component';
import { PizzasComponent } from '../pizzas/pizzas.component';

const routes: Routes = [
  { path: 'ingredientes', children: [
    { path: '', component: IngredientesComponent },
    // { path: 'add', component: BlogAddComponent },
    // { path: ':id/edit', component: BlogEditComponent },
    // { path: ':id', component: BlogViewComponent },
    // { path: ':id/:kk', component: BlogViewComponent },
  ]},
  { path: 'pizzas', children: [
    // { path: '', component: BlogListComponent },
    // { path: 'add', component: BlogAddComponent },
    // { path: ':id/edit', component: BlogEditComponent },
    // { path: ':id', component: BlogViewComponent },
    // { path: ':id/:kk', component: BlogViewComponent },
  ]},
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule, IngredientesModule, PizzasComponent
  ]
})
export class ManagerModule { }
