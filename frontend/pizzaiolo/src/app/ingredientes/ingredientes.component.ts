import { Component, OnInit } from '@angular/core';
import { IngredientesEditables } from '../model/pizzaiolo/ingredientesEditables';
import { IngredientesService } from '../services/ingredientes.service';
import {MessageService} from 'primeng/api';

interface ITipoIngrediente {
  name: string
}
interface IMensajesError {
  severity: string;
  summary: string;
  detail: string;
}


@Component({
  selector: 'app-ingredientes',
  templateUrl: './ingredientes.component.html',
  styleUrls: ['./ingredientes.component.scss'],
  providers: [MessageService]
}
)

export class IngredientesComponent implements OnInit {

  ingredienteDialog: boolean = false;
  ingredientes: any = [];
  ingrediente: IngredientesEditables = { idIngredient: -1};
  submitted: boolean = false;

  tiposIngredientes: ITipoIngrediente[] = [];
  tipoIngredienteSelected: any = {};

  constructor(public restApi: IngredientesService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.restApi.getIngredientes().subscribe(
        ( data: {} ) => {
          this.ingredientes = data
          // console.log(this.ingredientes)
        }
    );
    this.tiposIngredientes = [
        { name: 'base' },
        { name: 'salsa' },
        { name: 'otros' },
      ]
  }

  openNew() {
    this.ingrediente = {idIngredient: 0, price:0.0};
    this.submitted = false;
    this.ingredienteDialog = true;
  }

  openEdit(ingrediente: IngredientesEditables) {
    this.ingrediente = { ...ingrediente }
    this.ingredienteDialog = true;

  }

  saveIngrediente(){

    const ingredienteToBD = {
      ...this.ingrediente,
      type: this.tipoIngredienteSelected.name
    }
    // if( this.ingrediente.price === 0.0){
    //   this.messageService.add({severity:'error', summary: 'Error', detail: 'El precio tiene que ser mayor que cero'});
    //   return
    // }
    // if( this.ingrediente.name === ""){
    //   this.messageService.add({severity:'error', summary: 'Error', detail: 'La cantidad tiene que ser mayor que cero'});
    //   return
    // }

    if( this.ingrediente.name?.trim() !== ''  ){
      if( this.ingrediente.idIngredient > 0 ){
        this.restApi.updateIngrediente(this.ingrediente.idIngredient, ingredienteToBD).subscribe(
          (data: {}) => {
            this.restApi.getIngredientes().subscribe(
              ( data: {} ) => {
                this.ingredientes = data
                // console.log(this.ingredientes)
              }
            );
          }
        )
      }else{
        //createIngrediente
        this.restApi.createIngrediente(ingredienteToBD).subscribe(
          (data: {}) => {
            this.restApi.getIngredientes().subscribe(
              ( data: {} ) => {
                this.ingredientes = data
                // console.log(this.ingredientes)
              }
            );
          }
        )
      }
    }

    this.ingredienteDialog = false;
    this.submitted = false;
  }

  hideDialog() {
    this.ingredienteDialog = false;
    this.submitted = false;
  }

  validarFormulario() {
    console.log('estoy validando')
    console.log(this.ingrediente)
    let errors: IMensajesError[] = [];

    if( this.ingrediente.price === 0.0){
      this.messageService.add({severity:'error', summary: 'Error', detail: 'El precio tiene que ser mayor que cero'});
      return
    }

    if(this.ingredientes.find((x: any) => x.name === this.ingrediente.name)){
      errors.push({
        severity:'error', summary:'Nombre Incorrecto', detail:'El nombre de un ingrediente no se puede repetir'
      })
    }

    if(errors.length > 0){
      this.messageService.addAll(errors);
      return false
    }

    return true;

  }

}

