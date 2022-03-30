import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  { path: '', pathMatch: 'full', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'inicio', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'registro', component: RegistroComponent, data: { pageTitle: 'Registro' } },
  { path: 'login', component: LoginComponent, data: { pageTitle: 'Login' } },
  { path: 'pizzas', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'pizzas/:id', component: PizzasViewComponent, data: { pageTitle: 'Detalle Pizzas' } },
  { path: 'carrito', component: CarritoComponent, data: { pageTitle: 'Carrito' } },

  { path: 'user', children: [
    { path: '', component: UserComponent },
    { path: 'edit', component: UserEditComponent },
    { path: 'reset', component: UserResetComponent },
  ]},

  { path: 'admin', children: [
    { path: '', component: AdminComponent },
    { path: 'ingredientes', component: AdminIngredientesComponent },
    { path: 'pizzas', component: AdminPizzasComponent },
    { path: ':roles', component: AdminRolesComponent },
      ]},

  { path: 'tienda', children: [

    { path: 'cocina', component: CocinaComponent },
    { path: 'reparto', component: RepartoComponent },
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }