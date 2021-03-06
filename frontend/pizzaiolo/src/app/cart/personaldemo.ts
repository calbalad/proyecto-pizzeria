import { Component, OnInit } from '@angular/core';
import { TicketService } from './ticketservice';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { MegaMenuItem } from 'primeng/api';
import { Subscription } from 'rxjs';

@Component({
  template: `
    <div class="stepsdemo-content">
      <p-card>
        <p-table [value]="carts" responsiveLayout="scroll">
          <ng-template pTemplate="header">
            <tr>
              <th></th>
              <th>Pizza</th>
              <th>Precio</th>
              <th>Cantidad</th>
              <th>Total</th>
              <th></th>
            </tr>
          </ng-template>
          <ng-template pTemplate="body" let-product>
            <tr>
              <td>
                <img
                  [src]="
                    'http://localhost:8080/api/v1/pizzas/' +
                    product.idPizza +
                    '/foto'
                  "
                  [alt]="product.description"
                  width="100"
                  class="shadow-4"
                />
              </td>
              <td>{{ product.description }}</td>
              <td>{{ product.amount | currency: 'EUR' }}</td>
              <td>{{ product.quantity }}</td>
              <td>{{ product.quantity * product.amount | number: '1.2-2' }}</td>
              <td>
                <button
                  pButton
                  pRipple
                  type="button"
                  icon="pi pi-trash"
                  class="p-button-rounded p-button-danger"
                  (click)="remove(product)"
                ></button>
              </td>
            </tr>
          </ng-template>
          <ng-template pTemplate="summary">
            <div class="flex align-items-center justify-content-between">
              {{ carts ? carts.length : 0 }} productos. Total
              {{ total | number: '1.2-2' | currency: 'EUR' }}
            </div>
          </ng-template>
        </p-table>
        <ng-template pTemplate="footer">
          <div class="grid grid-nogutter justify-content-end">
            <p-button
              label="Siguiente"
              (onClick)="nextPage()"
              icon="pi pi-angle-right"
              iconPos="right"
            ></p-button>
          </div>
        </ng-template>
      </p-card>
    </div>
  `,
})
export class PersonalDemo implements OnInit {
  carts = [
    {
      quantity: 0,
      amount: 0,
    },
  ];
  total: number = 0;
  items: MenuItem[] = [];
  subscription!: Subscription;
  personalInformation: any;

  submitted: boolean = false;

  constructor(
    private cartService: CartService,
    private router: Router,
    public ticketService: TicketService
  ) {}

  ngOnInit(): void {
    this.total = 0;
    this.carts = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.carts);

    this.carts.forEach(product => this.total += product.amount * product.quantity)
  }

  remove(product: any) {
    this.carts = this.carts.filter((obj) => {
      return obj !== product;
    });
    localStorage.setItem('cart', JSON.stringify(this.carts));
    this.cartService.addCount();
    this.ngOnInit();
  }

  nextPage() {
    console.log(new Date().toISOString());
    // if (this.personalInformation.firstname && this.personalInformation.lastname && this.personalInformation.age) {
    this.ticketService.ticketInformation.personalInformation.amount =
      Math.round(this.total * 100) / 100;
    this.ticketService.ticketInformation.personalInformation.pizzas =
      this.carts;
    console.log(this.ticketService.ticketInformation.personalInformation);
    this.router.navigate(['carrito/direcciones']);

    return;
    // }

    this.submitted = true;
  }
}
