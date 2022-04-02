import { Component } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { CartService } from 'src/app/services/cart.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent  {
  items: MenuItem[] = [];
  countSub: any;
  cartCount!: number;
  carts: any;
  cartCount$: any;
  constructor(private cartService: CartService) {
    this.cartCount$ = this.cartService.cartCount$
  }

    ngOnInit() {
      this.items = [
          {
            label:'Admin',
            icon:'pi pi-fw pi-lock'
          },
          {
            label:'Login',
            icon:'pi pi-fw pi-sign-in',
            routerLinkActiveOptions: 'active',
            routerLink : "/login"
          },{
            label:'Registro',
            icon:'pi pi-fw pi-user',
            routerLinkActiveOptions: 'active',
            routerLink : "/registro"
          },{
            label:'Pizzas',
            icon:'pi pi-spin pi-star',
            routerLinkActiveOptions: 'active',
            routerLink : "/"
          },{
            label:'Pedidos',
            icon:'pi pi-fw pi-send',
            routerLinkActiveOptions: 'active',
            routerLink : "/pedidos",
            visible: true
          },{
            label:'Mi Perfil',
            icon:'pi pi-fw pi-cog',
            routerLinkActiveOptions: 'active',
            routerLink : "/user"
          },{
            label:'Cocina',
            icon:'pi pi-fw pi-inbox',
            routerLinkActiveOptions: 'active',
            routerLink : "/tienda/cocina"
          },{
            label:'Reparto',
            icon:'pi pi-fw pi-globe',
            routerLinkActiveOptions: 'active',
            routerLink : "/tienda/reparto"
          },
    ];


}

}
