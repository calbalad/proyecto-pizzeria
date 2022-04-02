import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})
export class CarritoComponent implements OnInit {
  carts = [{
    "quantity": 0,
  }];
  total: number = 0;

  constructor() { }

  ngOnInit(): void {
    this.carts = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.carts)
    this.total = this.carts.map(amount => amount.quantity).reduce((acc, amount) => amount + acc);
  }

}
