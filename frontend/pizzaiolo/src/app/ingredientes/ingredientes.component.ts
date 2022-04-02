import { Component, OnInit } from '@angular/core';
import { IngredientesService } from '../services/ingredientes.service';

@Component({
  selector: 'app-ingredientes',
  templateUrl: './ingredientes.component.html',

  styleUrls: ['./ingredientes.component.scss']
})
export class IngredientesComponent implements OnInit {

  ingredientes: any = [];
  constructor(public restApi: IngredientesService) { }

  ngOnInit(): void {
    this.restApi.getIngredientes().subscribe(
        ( data: {} ) => {
          this.ingredientes = data
          console.log(this.ingredientes)
        }
      );
  }


}
