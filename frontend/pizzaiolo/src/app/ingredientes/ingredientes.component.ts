import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ingredientes',
  templateUrl: './ingredientes.component.html',

  styleUrls: ['./ingredientes.component.scss']
})
export class IngredientesComponent implements OnInit {

  product = {
    data: [
      {
        id: '1000',
        code: 'f230fh0g3',
        name: 'Bamboo Watch',
        description: 'Product Description',
        image: 'https://picsum.photos/200/200',
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
        image: 'https://picsum.photos/200/200',
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
        image: 'https://picsum.photos/200/200',
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
        image: 'https://picsum.photos/200/200',
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
        image: 'https://picsum.photos/200/200',
        price: 79,
        category: 'Pizzas',
        quantity: 2,
        inventoryStatus: 'LOWSTOCK',
        rating: 3,
      }
    ]
  }

  constructor() { }

  ngOnInit(): void {
  }

}
