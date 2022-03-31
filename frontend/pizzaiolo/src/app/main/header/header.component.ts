import { Component } from '@angular/core';
import {MenuItem} from 'primeng/api';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent  {
  items: MenuItem[] = [];

    ngOnInit() {
      this.items = [
          {
            label:'Admin',
            icon:'pi pi-fw pi-lock'
          },
          {
            label:'Login',
            icon:'pi pi-fw pi-sign-in'
          },{
            label:'Registro',
            icon:'pi pi-fw pi-user'
          },{
            label:'Pizzas',
            icon:'pi pi-spin pi-star'
          },{
            label:'Carrito',
            icon:'pi pi-fw pi-shopping-cart'
          },{
            label:'Pedidos',
            icon:'pi pi-fw pi-send'
          },{
            label:'User',
            icon:'pi pi-fw pi-cog'
          },{
            label:'Cocina',
            icon:'pi pi-fw pi-inbox'
          },{
            label:'Reparto',
            icon:'pi pi-fw pi-globe'
          },
    ];
}

}
