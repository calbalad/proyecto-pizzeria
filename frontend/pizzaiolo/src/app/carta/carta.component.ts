import { Component, OnInit } from '@angular/core';
import {} from 'primeng';
import { SelectItem } from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';
import { RestApiService } from '../services/api.service';

@Component({
  selector: 'app-carta',
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.scss'],
})
export class CartaComponent implements OnInit {
  sortOptions: SelectItem[] = [];
  sortOrder: number = 0;
  sortField: string = '';
  pizzas: any = [];

  constructor(private primengConfig: PrimeNGConfig, public restApi: RestApiService) { }

  ngOnInit() {
    this.restApi.getPizzasCortas().subscribe((data: {}) => {
      this.pizzas = data
      console.log(this.pizzas)
    });
    this.primengConfig.ripple = true;
  }

  search(product: any) {
    var marvelHeroes =  this.pizzas.filter(function(hero: { description: any; }) {
      return hero.description
  });
  console.log(marvelHeroes)
  }
}
