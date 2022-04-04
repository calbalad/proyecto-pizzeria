import { Component, OnInit } from '@angular/core';
import { TicketService } from './ticketservice';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { RestApiService } from '../services/api.service';
import { CreateAddressParam } from '../model/authService/createAddressParam';

@Component({
  template: `
    <div class="stepsdemo-content">
      <p-card>
        <div class="card">
          <p-toolbar styleClass="mb-4">
            <ng-template pTemplate="left">
              <button
                pButton
                pRipple
                label="Nueva dirección"
                icon="pi pi-plus"
                class="p-button-success mr-2"
                (click)="openNew()"
              ></button>
            </ng-template>
          </p-toolbar>

          <p-table
            #dt
            [value]="user.data.address"
            selectionMode="single"
            [(selection)]="product"
            [rows]="10"
            [paginator]="false"
            responsiveLayout="scroll"
            [rowHover]="true"
            dataKey="id"
          >
            <ng-template pTemplate="header" let-columns>
              <tr>
                <th>Nombre</th>
                <th>Calle</th>
              </tr>
            </ng-template>
            <ng-template pTemplate="body" let-user let-columns="columns">
              <tr [pSelectableRow]="user">
                <td>{{ user.name }}</td>
                <td>
                  {{ user.street }}, {{ user.number }}, {{ user.city }},
                  {{ user.location }}, {{ user.postalCode }}
                </td>
              </tr>
            </ng-template>
          </p-table>
        </div>

        <p-dialog
          [(visible)]="productDialog"
          [style]="{ width: '450px' }"
          header="Product Details"
          [modal]="true"
          styleClass="p-fluid"
        >
          <ng-template pTemplate="content">
            <div class="field">
              <label for="name">Name</label>
              <input
                type="text"
                pInputText
                id="name"
                [(ngModel)]="product.name"
                required
                autofocus
              />
              <small class="p-error" *ngIf="submitted && !product.name"
                >Name is required.</small
              >
            </div>
            <div class="field">
              <label for="description">street</label>
              <input
                type="text"
                pInputText
                id="street"
                [(ngModel)]="product.street"
                required
              />
              <small class="p-error" *ngIf="submitted && !product.street"
                >street is required.</small
              >
            </div>
            <div class="field">
              <label for="description">Location</label>
              <input
                type="text"
                pInputText
                id="location"
                [(ngModel)]="product.location"
                required
              />
              <small class="p-error" *ngIf="submitted && !product.location"
                >location is required.</small
              >
            </div>
            <div class="field">
              <label for="description">number</label>
              <p-inputNumber
                type="number"
                id="number"
                [(ngModel)]="product.number"
                [useGrouping]="false"
              ></p-inputNumber>
              <small class="p-error" *ngIf="submitted && !product.number"
                >number is required.</small
              >
            </div>
            <div class="field">
              <label for="description">postalCode</label>
              <p-inputNumber
                type="number"
                id="postalCode"
                [(ngModel)]="product.postalCode"
                [useGrouping]="false"
              ></p-inputNumber>
              <small class="p-error" *ngIf="submitted && !product.postalCode"
                >postalCode is required.</small
              >
            </div>
          </ng-template>
          <ng-template pTemplate="footer">
            <button
              pButton
              pRipple
              label="Cancel"
              icon="pi pi-times"
              class="p-button-text"
              (click)="hideDialog()"
            ></button>
            <button
              pButton
              pRipple
              label="Save"
              icon="pi pi-check"
              class="p-button-text"
              (click)="saveProduct()"
            ></button>
          </ng-template>
        </p-dialog>
        <ng-template pTemplate="footer">
          <div class="grid grid-nogutter justify-content-between">
            <p-button
              label="Back"
              (onClick)="prevPage()"
              icon="pi pi-angle-left"
            ></p-button>
            <p-button
              label="Next"
              (onClick)="nextPage()"
              icon="pi pi-angle-right"
              iconPos="right"
              [disabled]="!product"
            ></p-button>
          </div>
        </ng-template>
      </p-card>
    </div>
  `,
})
export class ConfirmationDemo implements OnInit {
  ticketInformation: any;
  productDialog!: boolean;

  user: any;

  products: any;

  product!: CreateAddressParam;

  selectedProducts: any[] = [];

  submitted: boolean | undefined;

  statuses: any[] = [];

  carts = [
    {
      quantity: 0,
      amount: 0,
    },
  ];

  total: number = 0;

  constructor(
    private messageService: MessageService,
    public ticketService: TicketService,
    private router: Router,
    private restApi: RestApiService
  ) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('data') || '[]');
    this.carts = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.carts);
    this.total = this.carts
      .map((amount) => amount.amount)
      .reduce((acc, amount) => amount + acc);
  }

  complete() {
    this.ticketService.complete();
  }

  prevPage() {
    this.router.navigate(['carrito/carrito']);
  }

  nextPage() {
    this.ticketService.ticketInformation.personalInformation.address = this.product.street + ", " + this.product.number + ", " + this.product.location + ", " + this.product.city + ", " + this.product.postalCode;
    this.ticketService.ticketInformation.personalInformation.idUser = this.user.data.id
    this.router.navigate(['carrito/payment']);
    console.log(this.product);
  }

  openNew() {
    this.product = {
      city: '',
      location: '',
      name: '',
      number: 0,
      street: '',
      postalCode: 0,
    };
    this.submitted = false;
    this.productDialog = true;
  }

  editProduct(product: any) {
    this.product = { ...product };
    this.productDialog = true;
  }

  hideDialog() {
    this.productDialog = false;
    this.submitted = false;
  }
  saveProduct() {
    this.submitted = true;
    console.log(this.product);
    this.restApi
      .createAddress(this.product, this.user.data.id)
      .subscribe((data: {}) => {
        this.restApi
        .getUserAuthenticated()
        .subscribe((data: {}) => {
          localStorage.setItem('data', JSON.stringify(data));
          this.productDialog = false;
        console.log(data);
        this.product = {
          city: '',
          location: '',
          name: '',
          number: 0,
          street: '',
          postalCode: 0,
        };

        this.ngOnInit();
        console.log(this.product);
        });
      });

  }
}
