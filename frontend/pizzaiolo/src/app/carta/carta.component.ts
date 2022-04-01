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

  responsiveOptions: {
    breakpoint: string;
    numVisible: number;
    numScroll: number;
  }[];
  constructor(private primengConfig: PrimeNGConfig, public restApi: RestApiService) {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 3,
        numScroll: 3,
      },
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2,
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }

  ngOnInit() {
    this.sortOptions = [
      { label: 'Price High to Low', value: '!price' },
      { label: 'Price Low to High', value: 'price' },
    ];
    this.restApi.getPizzasCortas().subscribe((data: {}) => {
      this.pizzas = data
      console.log(this.pizzas)
    });



    this.primengConfig.ripple = true;
  }
}
