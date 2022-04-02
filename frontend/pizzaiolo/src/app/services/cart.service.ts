import { Injectable } from '@angular/core';
import { ReplaySubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cartCount = new ReplaySubject<number>(1);
  cartCount$ = this.cartCount.asObservable();
  setCartCount(count: number) {
    // encapsulate with logic to set local storage
    localStorage.setItem("cart_count", JSON.stringify(count));
    this.cartCount.next(count);
  }

  constructor() {
    // can check local storage here to initialize?
  }
}
