import { Component, Injectable } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { LoginService } from 'src/app/services/log.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})


export class HeaderComponent  {
  constructor(private auth: LoginService){
  }

  items: MenuItem[] = [];

    ngOnInit() {
      this.items = [
          {
            label:'Admin',
            icon:'pi pi-fw pi-lock',
            visible: this.auth.isLoggedIn(),
          },
          {
            label:'Login',
            icon:'pi pi-fw pi-sign-in',
            routerLinkActiveOptions: 'active',
            routerLink : "/login",
            visible: !this.auth.isLoggedIn(),
          },{
            label:'Registro',
            icon:'pi pi-fw pi-user',
            routerLinkActiveOptions: 'active',
            routerLink : "/registro",
            visible: !this.auth.isLoggedIn(),
          },{
            label:'Pizzas',
            icon:'pi pi-spin pi-star',
            routerLinkActiveOptions: 'active',
            routerLink : "/"
          },{
            label:'Carrito',
            icon:'pi pi-fw pi-shopping-cart',
            routerLinkActiveOptions: 'active',
            routerLink : "/carrito",
            visible: this.auth.isLoggedIn(),
          },{
            label:'Pedidos',
            icon:'pi pi-fw pi-send',
            routerLinkActiveOptions: 'active',
            routerLink : "/pedidos",
            visible: this.auth.isLoggedIn(),
          },{
            label:'Mi Perfil',
            icon:'pi pi-fw pi-cog',
            routerLinkActiveOptions: 'active',
            routerLink : "/user",
            visible: this.auth.isLoggedIn(),
          },{
            label:'Cocina',
            icon:'pi pi-fw pi-inbox',
            routerLinkActiveOptions: 'active',
            routerLink : "/tienda/cocina",
            visible: this.auth.isLoggedIn(),
          },{
            label:'Reparto',
            icon:'pi pi-fw pi-globe',
            routerLinkActiveOptions: 'active',
            routerLink : "/tienda/reparto",
            visible: this.auth.isLoggedIn(),
          },
          {
            label:'Desconectar',
            icon:'pi pi-fw pi-sign-out',
            routerLinkActiveOptions: 'active',
            routerLink : "/logout",
            visible: this.auth.isLoggedIn(),
            command : ((event?: any) =>this.auth.doLogout()),
          },
    ];
}

}
