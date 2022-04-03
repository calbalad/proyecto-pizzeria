import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})
export class CarritoComponent implements OnInit {
  carts = [{
    "quantity": 0,
    "amount": 0
  }];
  total: number = 0;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.carts = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.carts)
    this.total = this.carts.map(amount => amount.amount).reduce((acc, amount) => amount + acc);
  }

  remove(product: any){
    this.carts = this.carts.filter(obj => {return obj !== product});
    localStorage.setItem('cart', JSON.stringify(this.carts))
    this.cartService.addCount();
  }

}
