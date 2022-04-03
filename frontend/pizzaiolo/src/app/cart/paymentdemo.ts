import { Component, OnInit } from '@angular/core';
import { TicketService } from './ticketservice';
import { Router } from '@angular/router';
import { RestApiService } from '../services/api.service';

@Component({
  template: `
    <div class="stepsdemo-content">
      <p-card>
        <ng-template pTemplate="title"> Resumen del pedido </ng-template>
        <p-table [value]="orders" responsiveLayout="scroll">
          <ng-template pTemplate="header">
            <tr>
              <th></th>
              <th>Pizza</th>
              <th>Price</th>
              <th>Cantidad</th>
              <th>Total</th>
              <th></th>
            </tr>
          </ng-template>
          <ng-template pTemplate="body" let-order>
            <tr>
              <td>
                <img
                  [src]="
                    'http://localhost:8080/api/v1/pizzas/' +
                    order.idPizza +
                    '/foto'
                  "
                  [alt]="order.description"
                  width="100"
                  class="shadow-4"
                />
              </td>
              <td>{{ order.description }}</td>
              <td>{{ order.amount | currency: 'EUR' }}</td>
              <td>{{ order.quantity }}</td>
              <td>{{ order.quantity * order.amount | number: '1.2-2' }}</td>
            </tr>
          </ng-template>
          <ng-template pTemplate="summary">
            <div class="flex align-items-center justify-content-between">
              {{ orders ? orders.length : 0 }} products. Total
              {{ total | number: '1.2-2' | currency: 'EUR' }}
            </div>
          </ng-template>
        </p-table>

        <ng-template pTemplate="footer">
          <h5>Comentarios</h5>
          <textarea [rows]="5" [cols]="60" [autoResize]="true" [(ngModel)]="comment" pInputTextarea></textarea>
          <div class="grid grid-nogutter justify-content-between">
            <p-button
              label="Back"
              (onClick)="prevPage()"
              icon="pi pi-angle-left"
            ></p-button>
            <p-button
              label="Finalizar"
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
export class PaymentDemo implements OnInit {
  orders: any;
  total: number = 0;
  comment = "";
  constructor(public ticketService: TicketService, private router: Router, private restApi: RestApiService) {}

  ngOnInit() {
    this.orders =
      this.ticketService.ticketInformation.personalInformation.pizzas;
    this.total = this.orders
      .map((amount: { amount: any }) => amount.amount)
      .reduce((acc: any, amount: any) => amount + acc);
    console.log(this.orders);
  }

  nextPage() {
    this.ticketService.ticketInformation.personalInformation.comment = this.comment;
    this.ticketService.ticketInformation.personalInformation.orderDate= new Date().toISOString().toString();
    console.log(JSON.stringify(this.ticketService.ticketInformation.personalInformation))
    this.restApi
      .createOrder(this.ticketService.ticketInformation.personalInformation)
      .subscribe((data: {}) => {
        console.log(data)
      });
    this.router.navigate(['pizza']);
  }

  prevPage() {
    this.router.navigate(['carrito/direcciones']);
  }
}
