import { Injectable } from '@angular/core';
import { ReplaySubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cartCount = new ReplaySubject<number>(1);
  count = [];
  simpleObservable = new Subject();
  simpleObservable$ = this.simpleObservable.asObservable();
  constructor() { }
  
  addCount() {
    this.count = JSON.parse(localStorage.getItem('cart') || '[]');
    this.simpleObservable.next(this.count.length)
    console.log(this.count)
  }

  getCount(){
    return this.simpleObservable$;
  }
}
