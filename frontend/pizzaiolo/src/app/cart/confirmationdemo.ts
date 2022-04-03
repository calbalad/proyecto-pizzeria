import {Component,OnInit} from '@angular/core';
import { TicketService } from './ticketservice';
import { Router } from '@angular/router';
import {  MessageService } from 'primeng/api';


@Component({
    template: `
        <div class="stepsdemo-content">
            <p-card>
            <div class="card">
    <p-toolbar styleClass="mb-4">
        <ng-template pTemplate="left">
            <button pButton pRipple label="New" icon="pi pi-plus" class="p-button-success mr-2" (click)="openNew()"></button>
        </ng-template>
    </p-toolbar>

    <p-table #dt [value]="carts" selectionMode="single" [(selection)]="products" [rows]="10" [paginator]="false"  responsiveLayout="scroll"
         [rowHover]="true" dataKey="id"
        >
        <!-- <ng-template pTemplate="header">
            <tr>
                <th style="width: 3rem">
                    <p-tableHeaderCheckbox></p-tableHeaderCheckbox>
                </th>
                <th pSortableColumn="name">Name </th>
                <th>Image</th>
                <th pSortableColumn="price">Price </th>
                <th pSortableColumn="category">Category </th>
                <th pSortableColumn="rating">Reviews </th>
                <th pSortableColumn="inventoryStatus">Status </th>
                <th></th>
            </tr>
        </ng-template> -->
        <ng-template pTemplate="body" let-product>
            <tr>
                <td [pSelectableRow]="product">
                    <p-tableCheckbox [value]="product"></p-tableCheckbox>
                </td>
                <td>{{product.description}}</td>
                <td><img [src]="'assets/showcase/images/demo/product/' + product.image" [alt]="product.name" width="100" class="shadow-4" /></td>
                <td>{{product.amount | currency:'USD'}}</td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <button pButton pRipple icon="pi pi-pencil" class="p-button-rounded p-button-success mr-2" (click)="editProduct(product)"></button>

                </td>
            </tr>
        </ng-template>
        <ng-template pTemplate="summary">
            <div class="flex align-items-center justify-content-between">
                In total there are {{carts ? carts.length : 0 }} products.
            </div>
        </ng-template>
    </p-table>
</div>

<p-dialog [(visible)]="productDialog" [style]="{width: '450px'}" header="Product Details" [modal]="true" styleClass="p-fluid">
    <ng-template pTemplate="content">

        <div class="field">
            <label for="name">Name</label>
        <!--     <input type="text" pInputText id="name" [(ngModel)]="product.name" required autofocus />
            <small class="p-error" *ngIf="submitted && !product.name">Name is required.</small> -->
        </div>
        <div class="field">
            <label for="description">Description</label>
            <!-- <textarea id="description" pInputTextarea [(ngModel)]="product.description" required rows="3" cols="20"></textarea> -->
        </div>
        <div class="field">
            <label for="inventoryStatus">Inventory Status</label>
           <!--  <p-dropdown [(ngModel)]="product.inventoryStatus" inputId="inventoryStatus" [options]="statuses" placeholder="Select">
                <ng-template let-option pTemplate="item">
                    <span [class]="'product-badge status-' + option.value">{{option.label}}</span>
                </ng-template>
            </p-dropdown> -->
        </div>

        <div class="field">
            <label class="mb-3">Category</label>
            <!-- <div class="formgrid grid">
                <div class="field-radiobutton col-6">
                    <p-radioButton id="category1" name="category" value="Accessories" [(ngModel)]="product.category"></p-radioButton>
                    <label for="category1">Accessories</label>
                </div>
                <div class="field-radiobutton col-6">
                    <p-radioButton id="category2" name="category" value="Clothing" [(ngModel)]="product.category"></p-radioButton>
                    <label for="category2">Clothing</label>
                </div>
                <div class="field-radiobutton col-6">
                    <p-radioButton id="category3" name="category" value="Electronics" [(ngModel)]="product.category"></p-radioButton>
                    <label for="category3">Electronics</label>
                </div>
                <div class="field-radiobutton col-6">
                    <p-radioButton id="category4" name="category" value="Fitness" [(ngModel)]="product.category"></p-radioButton>
                    <label for="category4">Fitness</label>
                </div>
            </div> -->
        </div>


    </ng-template>

    <ng-template pTemplate="footer">
        <button pButton pRipple label="Cancel" icon="pi pi-times" class="p-button-text" (click)="hideDialog()"></button>
        <button pButton pRipple label="Save" icon="pi pi-check" class="p-button-text" (click)="saveProduct()"></button>
    </ng-template>
</p-dialog>
<ng-template pTemplate="footer">
                    <div class="grid grid-nogutter justify-content-between">
                        <p-button label="Back" (onClick)="prevPage()" icon="pi pi-angle-left"></p-button>
                        <p-button label="Next" (onClick)="nextPage()" icon="pi pi-angle-right" iconPos="right"></p-button>
                    </div>
                </ng-template>
            </p-card>
        </div>
    `,
})
export class ConfirmationDemo implements OnInit {
    ticketInformation: any;
    productDialog!: boolean;

    products: any;

    product = {};

    selectedProducts: any[] = [] ;

    submitted: boolean | undefined;

    statuses: any[] = [];

    carts = [{
      "quantity": 0,
      "amount": 0
    }];

    total: number = 0;

    constructor(private messageService: MessageService, public ticketService: TicketService, private router: Router) { }

    ngOnInit() {
        this.ticketInformation = this.ticketService.ticketInformation;
        this.statuses = [
          {label: 'INSTOCK', value: 'instock'},
          {label: 'LOWSTOCK', value: 'lowstock'},
          {label: 'OUTOFSTOCK', value: 'outofstock'}
      ];
      this.carts = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.carts)
    this.total = this.carts.map(amount => amount.amount).reduce((acc, amount) => amount + acc);

      }

    complete() {
        this.ticketService.complete();
    }

    prevPage() {
        this.router.navigate(['carrito/carrito']);
    }

    nextPage() {
      //this.ticketService.ticketInformation.paymentInformation = this.paymentInformation;
      this.router.navigate(['carrito/payment']);
  }

    openNew() {
      this.product = {};
      this.submitted = false;
      this.productDialog = true;
  }



  editProduct(product: any) {
      this.product = {...product};
      this.productDialog = true;
  }


hideDialog() {
  this.productDialog = false;
  this.submitted = false;
}
saveProduct() {
  this.submitted = true;

}

}
