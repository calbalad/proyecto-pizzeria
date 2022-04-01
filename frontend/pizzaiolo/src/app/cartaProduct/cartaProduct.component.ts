import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestApiService } from '../services/api.service';
import { CestaEditDTO } from '../model/pizzaiolo/models';

@Component({
  selector: 'app-cartaProduct',
  templateUrl: './cartaProduct.component.html',
  styleUrls: ['./cartaProduct.component.scss'],
})
export class CartaProductComponent implements OnInit {
  public id: any;
  producto!: any;
  cart: CestaEditDTO[] = [];
  constructor(private route: ActivatedRoute, public restApi: RestApiService) {}

  ngOnInit() {
    this.restApi
      .getPizzasDetalladas(this.route.snapshot.paramMap.get('id'))
      .subscribe((data: {}) => {
        this.producto = data;
        console.log(this.producto);
      });
    this.cart = JSON.parse(localStorage.getItem('cart') || '[]');
    console.log(this.cart);
  }

  addToCart() {
    this.cart.push({ amount: 2, idOrder: 0, idPizza: 1, quantity: 0 });
    localStorage.setItem('cart', JSON.stringify(this.cart));
  }
}
