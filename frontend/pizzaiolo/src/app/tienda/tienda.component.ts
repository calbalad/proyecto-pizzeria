import { Component, OnInit } from '@angular/core';
import { OrderEditDTO } from '../model/pizzaiolo/orderEditDTO';
import { OrderStatusEditDTO } from '../model/pizzaiolo/orderStatusEditDTO';
import { PedidosService } from '../services/pedidos.service';
import { TiendaModule } from '../tienda';

@Component({
  selector: 'app-tienda',
  templateUrl: './tmpl-cocina.component.html',
  styleUrls: ['./tienda.component.scss'],
})
export class CocinaComponent implements OnInit {
  pedidoDialog: boolean = false;
  pedidos: any = [];
  pedido: OrderStatusEditDTO = { idOrder: -1 };
  submitted: boolean = false;

  constructor(public restApi: PedidosService) {}

  ngOnInit(): void {
    this.restApi.getPedidosSolicitados().subscribe((data: {}) => {
      this.pedidos = this.sortData(data);
    });
  }

  sortData(data: any) {
    return data.sort((b: any, a: any) => {
      return <any>new Date(b.orderDate) - <any>new Date(a.orderDate);
    });
  }

  changeStatus() {
    console.log(this.pedido.idOrder);
    if (this.pedido.idOrder) {
      this.pedido.orderStatus = OrderStatusEditDTO.OrderStatusEnum.Elaborandose;
      this.pedido.idChef = "10";
      console.log(this.pedido);
      this.restApi
        .updatePedido(this.pedido.idOrder, this.pedido)
        .subscribe((data: {}) => {
          this.ngOnInit();
        });
    }
    this.pedidoDialog = false;
  }

  openEdit(pedido: OrderEditDTO) {
    this.pedido = { ...pedido };
    console.log(pedido);
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
  styleUrls: ['./tienda.component.scss'],
})
export class RepartoComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
