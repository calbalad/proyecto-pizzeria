import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestApiService } from '../services/api.service';

@Component({
  selector: 'app-cartaProduct',
  templateUrl: './cartaProduct.component.html',
  styleUrls: ['./cartaProduct.component.scss']
})

export class CartaProductComponent implements OnInit {
  public id: any;
  producto!: any;
  constructor(private route: ActivatedRoute, public restApi: RestApiService) {

  }

  ngOnInit() {
    this.restApi.getPizzasCortas(this.route.snapshot.paramMap.get('id')).subscribe((data: {}) => {
      this.producto = data;
      console.log(this.producto)
    });
 }

}
