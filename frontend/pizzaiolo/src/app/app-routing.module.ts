import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './main/header/header.component';
import { CartaProductComponent } from './cartaProduct/cartaProduct.component';
import { CartaComponent } from './carta/carta.component';
import { RegistroComponent } from './security/registro/registro.component';
import { LoginComponent } from './login/login.component';
import { CarritoComponent } from './carrito/carrito.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { UserEditComponent, UserResetComponent } from './user/user.component';

import { ManagerComponent } from './manager/manager.component';

import { SesionGuardGuard } from './sesion-guard.guard';
import { CocinaComponent, RepartoComponent } from './tienda/tienda.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', component: CartaComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'pizza/:id',canActivate: [SesionGuardGuard], pathMatch: 'full', component: CartaProductComponent, data: { pageTitle: 'Pizza' } },
  { path: 'pizza',canActivate: [SesionGuardGuard], pathMatch: 'full', component: CartaComponent, data: { pageTitle: 'Pizzas' } },
  // { path: '', pathMatch: 'full', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  // { path: 'inicio', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  { path: 'registro', component: RegistroComponent, data: { pageTitle: 'Registro' } },
  { path: 'login', component: LoginComponent, data: { pageTitle: 'Login' } },
  // { path: 'pizzas', component: PizzasComponent, data: { pageTitle: 'Pizzas' } },
  // { path: 'pizzas/:id', component: PizzasViewComponent, data: { pageTitle: 'Detalle Pizzas' } },
 // { path: 'carritote',canActivate: [SesionGuardGuard], component: CarritoComponent, data: { pageTitle: 'Carrito' } },
  { path: 'pedidos',canActivate: [SesionGuardGuard], component: PedidosComponent, data: { pageTitle: 'Pedidos' } },
  { path: 'manager',canActivate: [SesionGuardGuard], loadChildren: () => import('./manager/manager.module').then(mod => mod.ManagerModule)},
  { path: 'carrito',canActivate: [SesionGuardGuard], loadChildren: () => import('./cart/cart.module').then(mod => mod.CartModule)},

  { path: 'user', children: [
    { path: '',canActivate: [SesionGuardGuard], component: UserEditComponent },
    { path: 'edit',canActivate: [SesionGuardGuard], component: UserEditComponent },
    { path: 'reset',canActivate: [SesionGuardGuard], component: UserResetComponent },
  ]},

  // { path: 'manager', children: [
  //   { path: '', component: AdminComponent },
  //   { path: 'ingredientes', component: IngredientesComponent },
  //   { path: 'pizzas', component: PizzasComponent },
  //   { path: ':roles', component: RolesComponent },
  //     ]},

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
