import { Component, OnInit } from '@angular/core';
import { IngredientesEditables } from '../model/pizzaiolo/ingredientesEditables';
import { PizzasEditable } from '../model/pizzaiolo/pizzasEditable';
import { PizzasEditables } from '../model/pizzaiolo/pizzasEditables';
import { IngredientesService } from '../services/ingredientes.service';
import { PizzasService } from '../services/pizzas.service';

@Component({
  selector: 'app-pizzas',
  templateUrl: './pizzas.component.html',
  styleUrls: ['./pizzas.component.scss']
})
export class PizzasComponent implements OnInit {

  pizzaDialog: boolean = false;
  pizzas: any = [];
  pizza: PizzasEditables = { };
  submitted: boolean = false;

  ingredientesBase: any = [];
  ingredientesSalsa: any = [];
  ingredientes: any = [];
  ingredientesTarget: any = [];

  constructor(public restApi: PizzasService, public resApiIngredientes: IngredientesService) {  }

  ngOnInit(): void {
    // Cargamos todas las pizzas
    this.restApi.getPizzas().subscribe(
        ( data: {} ) => {
          this.pizzas = data
          console.log(this.pizzas)
        }
    );

    //Cargamos todos los ingredientes, ingredientesBase e ingredientesSalsas
    this.resApiIngredientes.getIngredientes().subscribe(
      ( data: any ) => {
        console.log(data)
        this.ingredientes = data
        this.ingredientesBase = data.filter((item: IngredientesEditables) => item.type === 'base' );
        this.ingredientesSalsa = data.filter((item: IngredientesEditables) => item.type === 'salsa' );
      }
  );

  }

  openNew() {
    // this.ingrediente = {};
    this.submitted = false;
    this.pizzaDialog = true;
  }

  openEdit(pizza: PizzasEditables) {

    const idPizza = pizza.idPizza ? pizza.idPizza : 0;

    this.restApi.getPizza(idPizza).subscribe(
      ( data: {} ) => {
        // this.pizzas = data
        this.pizza = data
        this.ingredientesTarget = this.pizza.ingredientPizza;
        console.log(this.pizza)
      }
    );
    // this.pizza = { ...pizza }
    // console.log(this.pizza)
    this.pizzaDialog = true;

  }

  savePizza() {

  }
  // saveIngrediente(){

  //   if( this.ingrediente.name?.trim() !== ''  ){
  //     if( this.ingrediente.idIngredient > 0 ){
  //       this.restApi.updateIngrediente(this.ingrediente.idIngredient, this.ingrediente).subscribe(
  //         (data: {}) => {
  //           this.restApi.getIngredientes().subscribe(
  //             ( data: {} ) => {
  //               this.ingredientes = data
  //               console.log(this.ingredientes)
  //             }
  //           );
  //         }
  //       )
  //     }else{
  //       //createIngrediente
  //       this.restApi.createIngrediente(this.ingrediente).subscribe(
  //         (data: {}) => {
  //           this.restApi.getIngredientes().subscribe(
  //             ( data: {} ) => {
  //               this.ingredientes = data
  //               console.log(this.ingredientes)
  //             }
  //           );
  //         }
  //       )
  //     }
  //   }

  //   this.ingredienteDialog = false;
  //   this.submitted = false;
  // }

  hideDialog() {
    this.pizzaDialog = false;
    this.submitted = false;
  }
}

