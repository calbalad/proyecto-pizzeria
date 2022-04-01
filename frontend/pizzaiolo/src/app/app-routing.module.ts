import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './main/header/header.component';
import { PizzaDetailComponent } from './pizza-detail/pizza-detail.component';
import { CartaComponent } from './carta/carta.component';
import { RegistroComponent } from './security/registro/registro.component';
import { LoginComponent } from './login/login.component';
import { CarritoComponent } from './carrito/carrito.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: CartaComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'pizza', pathMatch: 'full', component: CartaComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'pizza/:id', pathMatch: 'full', component: PizzaDetailComponent, data: { pageTitle: 'Pizza' } },
  // { path: '', pathMatch: 'full', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  // { path: 'inicio', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'registro', component: RegistroComponent, data: { pageTitle: 'Registro' } },
  { path: 'login', component: LoginComponent, data: { pageTitle: 'Login' } },
  // { path: 'pizzas', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  // { path: 'pizzas/:id', component: PizzasViewComponent, data: { pageTitle: 'Detalle Pizzas' } },
  { path: 'carrito', component: CarritoComponent, data: { pageTitle: 'Carrito' } },
  { path: 'manager', loadChildren: () => import('./manager/manager.module').then(mod => mod.ManagerModule)}

  // { path: 'user', children: [
  //   { path: '', component: UserComponent },
  //   { path: 'edit', component: UserEditComponent },
  //   { path: 'reset', component: UserResetComponent },
  // ]},

  // { path: 'manager', children: [
  //   { path: '', component: AdminComponent },
  //   { path: 'ingredientes', component: IngredientesComponent },
  //   { path: 'pizzas', component: PizzasComponent },
  //   { path: ':roles', component: RolesComponent },
  //     ]},

  // { path: 'tienda', children: [

  //   { path: 'cocina', component: CocinaComponent },
  //   { path: 'reparto', component: RepartoComponent },
  // ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
