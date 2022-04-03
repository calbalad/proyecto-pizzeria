import { Component, OnInit } from '@angular/core';
import { PedidosService } from '../services/pedidos.service';
import { OrderEditDTO } from '../model/pizzaiolo/orderEditDTO';
import {TiendaModule} from '../tienda';

@Component({
  selector: 'app-tienda',
  templateUrl: './tmpl-cocina.component.html',
  styleUrls: ['./tienda.component.scss']
})

export class CocinaComponent implements OnInit {

  pedidoDialog: boolean = false;
  pedidos: any = [];
  pedido: OrderEditDTO = { idOrder: -1};
  submitted: boolean = false;

  constructor(public restApi: PedidosService) {  }

  ngOnInit(): void {
    this.restApi.getPedidosSolicitados().subscribe(
        ( data: {} ) => {
          this.pedidos = data;
          console.log(this.pedidos)
        }
      );
  }

  openNew() {
    this.pedido = {idOrder: 0};
    this.submitted = false;
    this.pedidoDialog = true;
  }

  openEdit(pedido: OrderEditDTO) {
    this.pedido = { ...pedido }
    this.pedidoDialog = true;

  }

    hideDialog() {
        this.pedidoDialog = false;
        this.submitted = false;
    }


}


@Component({
  selector: 'app-tienda',
  templateUrl: './tmpl-reparto.component.html',
  styleUrls: ['./tienda.component.scss']
})
export class RepartoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

