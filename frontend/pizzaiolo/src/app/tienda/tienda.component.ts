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
  pedidoConfirmDialog: boolean = false;
  pedidoDeclineDialog: boolean = false;

  pedidos: any = [];
  pedido: OrderStatusEditDTO = { idOrder: -1 };

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

  changeStatus(operation: string) {
    console.log(this.pedido.idOrder);
    if (this.pedido.idOrder) {
      if (operation === 'confirm') {
        this.pedido.orderStatus =
          OrderStatusEditDTO.OrderStatusEnum.Elaborandose;
        this.pedido.idChef = '10';
        this.restApi
          .updatePedido(this.pedido.idOrder, this.pedido)
          .subscribe((data: {}) => {
            this.ngOnInit();
          });
      }
      else
      if (operation === 'decline') {
        console.log(this.pedido);
        this.restApi
          .deletePedido(this.pedido.idOrder)
          .subscribe((data: {}) => {
            this.ngOnInit();
          });
      }
      else throw new Error("Operación no válida");
      this.hideDialog();
    }

  }

  openEdit(pedido: OrderStatusEditDTO, operation: string) {
    this.pedido = { ...pedido };

    if (operation === 'confirm') this.pedidoConfirmDialog = true
    else if (operation === 'decline') this.pedidoDeclineDialog = true;
    else throw new Error("Operación no válida")
  }

  hideDialog() {
    if (this.pedidoConfirmDialog) this.pedidoConfirmDialog = false;
    if (this.pedidoDeclineDialog) this.pedidoDeclineDialog = false;
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
