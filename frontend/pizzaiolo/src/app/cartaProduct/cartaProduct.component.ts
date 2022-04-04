import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestApiService } from '../services/api.service';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-cartaProduct',
  templateUrl: './cartaProduct.component.html',
  styleUrls: ['./cartaProduct.component.scss'],
})
export class CartaProductComponent implements OnInit {
  public id: any;
  product!: any;
  cart: any[] = [];
  countSub: any;
  cartCount: any;
  constructor(private route: ActivatedRoute, public restApi: RestApiService, private cartService: CartService) {
  }



  ngOnInit() {
    this.restApi
      .getPizzasDetalladas(this.route.snapshot.paramMap.get('id'))
      .subscribe((data: {}) => {
        this.product = data;
        this.product.quantity = 1;
      });
    this.cart = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.cart);
  }

  addKeyValue(obj: any, key: string, data: number){
    obj[key] = data;
  }

  addToCart() {
    this.cart.push({ description: this.product.description, amount: this.product.amount, idOrder: 0, idPizza: this.product.idPizza, quantity: this.product.quantity });
    console.log(this.cart)
    localStorage.setItem('cart', JSON.stringify(this.cart));
    this.cartService.addCount();
  }
}

