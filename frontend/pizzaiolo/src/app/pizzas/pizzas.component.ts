import { Component, OnInit } from '@angular/core';
import {} from 'primeng';
import { MenuItem } from 'primeng/api';
import { SelectItem } from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-pizzas',
  templateUrl: './pizzas.component.html',
  styleUrls: ['./pizzas.component.scss'],
})
export class PizzasComponent implements OnInit {
  sortOptions: SelectItem[] = [];
  sortOrder: number = 0;

  sortField: string = "";

  products = {
    data: [
      {
        id: '1000',
        code: 'f230fh0g3',
        name: 'Bamboo Watch',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 65,
        category: 'Pizzas',
        quantity: 24,
        inventoryStatus: 'INSTOCK',
        rating: 5,
      },
      {
        id: '1001',
        code: 'nvklal433',
        name: 'Black Watch',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 72,
        category: 'Pizzas',
        quantity: 61,
        inventoryStatus: 'INSTOCK',
        rating: 4,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },{
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      },
      {
        id: '1002',
        code: 'zz21cz3c1',
        name: 'Blue Band',
        description: 'Product Description',
        image: 'https://picsum.photos/id/1/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      }
    ],
  };
  constructor( private primengConfig: PrimeNGConfig) { }

  ngOnInit() {
      this.sortOptions = [
          {label: 'Price High to Low', value: '!price'},
          {label: 'Price Low to High', value: 'price'}
      ];

      this.primengConfig.ripple = true;
  }
}
