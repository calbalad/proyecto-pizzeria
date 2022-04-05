import { Component, Injectable } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { CartService } from 'src/app/services/cart.service';
import { LoginService } from 'src/app/services/log.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})


export class HeaderComponent  {
  items: MenuItem[] = [];
  countSub: any;
  cartCount: any = 0;
  carts: any;

  blob: any = null;
  rol : any = '';
  constructor(private cartService: CartService, private auth: LoginService, private http: HttpClient, private sanitizer: DomSanitizer) { }
  getRole() {
    this.rol = JSON.parse(localStorage.getItem('data') || '[]');
    console.log(this.rol.data.role.name)
    return this.rol.data.role.name;
  }
    ngOnInit() {
      this.getBinary();
      this.items = [
          {
            label:'Admin',
            icon:'pi pi-fw pi-lock',
            visible: this.auth.isLoggedIn() && (this.getRole()=='ROLE_ADMIN'),
            routerLinkActiveOptions : "active",
            routerLink:"/manager"
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
            label:'Pedidos',
            icon:'pi pi-fw pi-send',
            routerLinkActiveOptions: 'active',
            routerLink : "/pedidos",
            visible: this.auth.isLoggedIn()
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
            visible: this.auth.isLoggedIn() && ((this.getRole()=='ROLE_ADMIN')||(this.getRole()=='ROLE_CHEF')),
          },{
            label:'Reparto',
            icon:'pi pi-fw pi-globe',
            routerLinkActiveOptions: 'active',
            routerLink : "/tienda/reparto",
            visible: this.auth.isLoggedIn() && ((this.getRole()=='ROLE_ADMIN')||(this.getRole()=='ROLE_DELIVERY')),
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

ngAfterContentInit(){
  this.cartCount = JSON.parse(localStorage.getItem('cart') || '[]').length
  this.cartService.getCount().subscribe(count => {
    this.cartCount = count
    console.log(count, "sdf")
    }
  );
  console.log(this.cartCount)
}

getBinary() {
  this.http.get('http://localhost:8080/api/v1/users/profile', { headers: { 'Authorization': 'Bearer ' + localStorage.getItem('access_token') }, responseType: 'blob'}).subscribe({
    next: (data: any) => {
      console.log(data)
      this.blob = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(data));
    }
  })
}

}
