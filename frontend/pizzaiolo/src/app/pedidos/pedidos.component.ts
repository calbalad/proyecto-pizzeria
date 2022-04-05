import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { PedidosService } from '../services/pedidos.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.scss']
})
export class PedidosComponent implements OnInit {
  user: any;
  orders: any = [];

  constructor(private orderService: PedidosService, private cartService: CartService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('data') || '[]');
    this.orderService.getPedidosUser(this.user.data.id).subscribe((data: {}) => {
      this.orders = data
      console.log(this.orders)
    });
  }



  addToCart(newProduct: any) {
    var cart = JSON.parse(localStorage.getItem('cart') || '[]');
    var hasMatch = false;
    for (var i = 0; i < cart.length; ++i) {
      var product = cart[i];
      if (product.idPizza == newProduct.idPizza) {
        hasMatch = true;
        cart[i].quantity += newProduct.quantity
        break;
      }
    }
    if (!hasMatch) {
      cart.push({
        description: newProduct.description,
        amount: newProduct.amount,
        idOrder: 0,
        idPizza: newProduct.idPizza,
        quantity: newProduct.quantity,
      });
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    this.cartService.addCount();
  }

  getClassStatus(order: any){
    if (order.orderStatus == 'enviado'){
      return 'primary'
    }else if (order == 'solicitado') {
      return 'instock'
    }else if (order == 'recibido') {
      return 'success'
    }else if (order == 'preparado') {
      return 'info'
    }else if (order == 'elaborandose') {
      return 'warning'
    }else if (order == 'cancelado') {
      return 'danger'
    }
    return 'primary'
  }
  neworder(order: any) {
    console.log("ordeeeeeer" + order  );
    
    for (let i = 0; i < order.length; i++) {
      console.log("entro" + order[i])
      console.log("entro" + order.pizzas[i])
      this.addToCart(order.pizzas[i].idPizza)
    }
  }
}
