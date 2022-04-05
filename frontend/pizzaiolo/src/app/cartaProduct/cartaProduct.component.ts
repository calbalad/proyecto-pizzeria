import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PizzasComponent } from '../pizzas/pizzas.component';
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
  pizzaComment: any;

  kk : PizzasComponent[] =[];

  constructor(
    private route: ActivatedRoute,
    public restApi: RestApiService,
    private cartService: CartService
  ) {}


  ngOnInit() {
    this.restApi
      .getPizzasDetalladas(this.route.snapshot.paramMap.get('id'))
      .subscribe((data: {}) => {
        this.product = data;
        this.product.quantity = 1;
      });
    this.cart = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.cart);

    this.restApi
      .getPizzasDetalladas(this.route.snapshot.paramMap.get('id'))
      .subscribe((data: {}) => {
        this.pizzaComment = data;
        this.pizzaComment = this.pizzaComment.comments
        console.log(this.pizzaComment.comments, 'en inicio')

      });
  }

  addKeyValue(obj: any, key: string, data: number) {
    obj[key] = data;
  }

  addToCart() {
    var hasMatch = false;
    for (var i = 0; i < this.cart.length; ++i) {
      var product = this.cart[i];
      if (product.idPizza == this.product.idPizza) {
        hasMatch = true;
        this.cart[i].quantity += this.product.quantity
        break;
      }
    }
    if (!hasMatch) {
      this.cart.push({
        description: this.product.description,
        amount: this.product.amount,
        idOrder: 0,
        idPizza: this.product.idPizza,
        quantity: this.product.quantity,
      });
    }
    localStorage.setItem('cart', JSON.stringify(this.cart));
    this.cartService.addCount();
  }
}
