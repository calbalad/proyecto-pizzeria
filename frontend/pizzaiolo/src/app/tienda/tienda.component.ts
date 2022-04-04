import { Component, OnInit } from '@angular/core';
import { OrderEditDTO } from '../model/pizzaiolo/orderEditDTO';
import { OrderStatusEditDTO } from '../model/pizzaiolo/orderStatusEditDTO';
import { PedidosService } from '../services/pedidos.service';
import { TiendaModule } from '../tienda';

import { ConfirmationService } from 'primeng/api';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-tienda',
  templateUrl: './tmpl-cocina.component.html',
  styleUrls: ['./tienda.component.scss'],
  providers: [ConfirmationService],
})
export class CocinaComponent implements OnInit {
  msgs: Message[] = [];
  pedidoConfirmDialog: boolean = false;
  pedidoDeclineDialog: boolean = false;

  pedidos: any = [];
  pedido: OrderStatusEditDTO = { idOrder: -1 };

  constructor(
    public restApi: PedidosService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.restApi.getPedidosSolicitados().subscribe((data: {}) => {
      this.pedidos = data;
    });
  }

  openEdit(pedido: OrderStatusEditDTO, operation: string) {
    this.pedido = { ...pedido };
    console.log(this.pedido);

    if (operation === 'confirm') this.statusConfirmed();
    else if (operation === 'decline') this.statusDeclined();
    else throw new Error('Operación no válida');
  }

  statusConfirmed() {
    this.confirmationService.confirm({
      message: '¿Quieres confirmar el pedido seleccionado?',
      header: 'Confirmación de pedido',
      icon: 'pi pi-info-circle',
      accept: () => {
        console.log("entro")
        console.log(this.pedido)

        if (this.pedido.idOrder) {
          this.pedido.orderStatus =
            OrderStatusEditDTO.OrderStatusEnum.Elaborandose;
          this.pedido.idChef = '10';
          this.restApi
            .updatePedido(this.pedido.idOrder, this.pedido)
            .subscribe((data: {}) => {
              this.ngOnInit();
            });

          this.msgs = [
            {
              severity: 'success',
              summary: 'Confirmed',
              detail: 'Pedido confirmado',
            },
          ];
        }
        else throw new Error("No existe ID del producto")
      },
      reject: () => {
        this.msgs = [
          {
            severity: 'error',
            summary: 'Rejected',
            detail: 'Operación abortada',
          },
        ];
      },
    });
  }

  statusDeclined() {
    this.confirmationService.confirm({
      message: '¿Quieres cancelar este pedido?',
      header: 'Confirmación de cancelar pedido',
      icon: 'pi pi-exclamation-triangle ',
      accept: () => {
        if (this.pedido.idOrder) {
        this.restApi.deletePedido(this.pedido.idOrder).subscribe((data: {}) => {
          this.ngOnInit();
        });

          this.msgs = [
            {
              severity: 'success',
              summary: 'Confirmed',
              detail: 'Pedido cancelado',
            },
          ];
        }
        else throw new Error('No existe ID del producto');
      },
      reject: () => {
        this.msgs = [
          {
            severity: 'error',
            summary: 'Rejected',
            detail: 'Operación abortada',
          },
        ];
      },
    });
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
