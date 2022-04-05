import { Component, OnInit } from '@angular/core';
import { IngredientesEditables } from '../model/pizzaiolo/ingredientesEditables';
import { PizzasEditable } from '../model/pizzaiolo/pizzasEditable';
import { PizzasEditables } from '../model/pizzaiolo/pizzasEditables';
import { IngredientesService } from '../services/ingredientes.service';
import { PizzasService } from '../services/pizzas.service';
import {MessageService} from 'primeng/api';

import { HttpClient} from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';

interface IIngredientesPizza {
  idIngredient: number;
  name: string;
  quantity: number;
}

interface IMensajesError {
  severity: string;
  summary: string;
  detail: string;
}

@Component({
  selector: 'app-pizzas',
  templateUrl: './pizzas.component.html',
  styleUrls: ['./pizzas.component.scss'],
  providers: [MessageService]
})
export class PizzasComponent implements OnInit {

  pizzaDialog: boolean = false;
  ingredientesDialog: boolean = false;

  pizzas: any = [];
  pizza: PizzasEditables = { };
  submitted: boolean = false;

  ingredientesBase: any = [];
  ingredientesSalsa: any = [];
  ingredientes: any = [];
  ingredientesTarget: any = [];

  ingredientesPizza: any = [];
  ingredientesDisponibles: any = []; //Borrar luego

  ingredienteAdd: any = {};

  idSauceSelected: any;
  idBaseSelected: any;

  fileURL: string = "";
  loading: boolean = false;
  file: File | null = null;
  blob: any = null;

  constructor(public restApi: PizzasService, public resApiIngredientes: IngredientesService, private messageService: MessageService,
    private http: HttpClient, private sanitizer: DomSanitizer) { }

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

  /* Eventos Pizza */
  openNew() {
    this.pizza = {description: ''}
    this.ingredientesTarget = [];
    this.submitted = false;
    this.pizzaDialog = true;
  }

  openEdit(pizza: PizzasEditables) {

    const idPizza = pizza.idPizza ? pizza.idPizza : 0;

    this.restApi.getPizza(idPizza).subscribe(
      ( data: {} ) => {
        this.pizza = data
        this.ingredientesTarget = this.pizza.ingredientPizza?.map((item: any) => {
          const ingredientePush = this.ingredientes.find((x: any) => x.idIngredient === item.idIngredient )
          return {idIngredient: item.idIngredient, name: ingredientePush.name, quantity: item.quantity};
        })
       }
    );
    this.pizzaDialog = true;

  }

  onChange(event: any) {
    this.file = event.target.files[0];
  }

  sendBinary(pizza: PizzasEditables) {
    if (!this.file) return;
    this.loading = !this.loading;
    console.log(this.file);
    const reader = new FileReader();
    reader.readAsArrayBuffer(this.file);
    reader.onload = () => {
      let url = 'http://localhost:8080/api/v1/pizzas/' + pizza.idPizza + '/foto';
      this.http.put(url, reader.result, { headers: { 'Content-Type': this.file ? this.file.type : '*/*' }, responseType: 'blob' }).subscribe({
        next: (data: any) => {
          this.fileURL = url;
          this.blob = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(data));
          this.loading = false;
        },
        error: err => { this.loading = false; console.error(err); }
      });
    };
    this.cargarPizzas();
  }

  savePizza() {

    if(!this.validarFormulario())  { return }

    this.pizza.ingredientPizza = this.ingredientesTarget;

    const pizzaToBD = {
      ...this.pizza,
      idSauce: this.idSauceSelected.idIngredient,
      idBase: this.idBaseSelected.idIngredient,
      active: true,
      amount: 1,
      netPrice: 1
    }

    // Si IdPizza existe estamos edit
    if(this.pizza.idPizza !== undefined ){
      const idPizza = pizzaToBD.idPizza ? pizzaToBD.idPizza : 0;
      this.restApi.updatePizza(idPizza, pizzaToBD).subscribe(
        ( data: {} ) => {
          this.cargarPizzas();
          this.pizzaDialog = false;
        }
      )
    }else{
      //Nueva Pizza
      console.log('Nueva Pizza')
      this.restApi.createPizza(pizzaToBD).subscribe(
        ( data: {} ) => {
          this.cargarPizzas();
          this.pizzaDialog = false;
        }
      )
    }

  }

  cargarPizzas() {
    this.restApi.getPizzas().subscribe(
      ( data: {} ) => {
        this.pizzas = data
      }
    );
  }



  /* eventos Dialogo Añadir ingredientes */
  openAddIngrediente() {
    this.ingredienteAdd = {};
    this.submitted = false;
    this.ingredientesDialog = true;
  }

  saveAddIngrediente() {
    if( !this.ingredientesTarget.find((x: any) => x.idIngredient === parseInt(this.ingredienteAdd.idIngredient)))
    {
      if(this.ingredienteAdd.quantity > 0 ){
        this.ingredientesTarget.push({
          idIngredient: parseInt(this.ingredienteAdd.idIngredient),
          name: this.ingredientes.find((x: any) => x.idIngredient === parseInt(this.ingredienteAdd.idIngredient)).name,
          quantity: this.ingredienteAdd.quantity
        })
        this.submitted = false;
        this.ingredientesDialog = false;
      }else{
        this.messageService.add({severity:'error', summary: 'Error', detail: 'La cantidad tiene que ser mayor que cero'});
      }
    }else{
      this.messageService.add({severity:'error', summary: 'Error', detail: 'El Ingrediente ya Existe'});
    }
  }

  deleteIngrediente(ingrediente: any) {
    this.ingredientesTarget = this.ingredientesTarget.filter((item: any) => item.idIngredient !== parseInt(ingrediente.idIngredient) )
  }

 /* general */

  hideDialog() {
    this.pizzaDialog = false;
    this.submitted = false;
    this.ingredientesDialog = false;
  }

  validarFormulario() {

    let errors: IMensajesError[] = [];

    if(this.pizza.description?.trim() === ''){
      errors.push({
        severity:'error', summary:'Descripción Incorrecta', detail:'La pizza tiene que tener una descripción'
      })
    }

   if(errors.length > 0){
      this.messageService.addAll(errors);
      return false
    }

    return true;

  }

}


