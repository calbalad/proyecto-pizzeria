import { Component, OnInit } from '@angular/core';
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
  pedidosSolicitados: any = [];
  pedidosElaborandose: any = [];
  pedidoSolicitado: OrderStatusEditDTO = { idOrder: -1 };
  pedidoElaborandose: OrderStatusEditDTO = { idOrder: -1 };


  constructor(
    public restApi: PedidosService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.restApi.getPedidosSolicitados().subscribe((data: {}) => {
      this.pedidosSolicitados = data;
    });
    this.restApi.getPedidosElaborandose().subscribe((data: {}) => {
      this.pedidosElaborandose = data;
    });
  }

  openEditSolicitado(pedido: OrderStatusEditDTO, operation: string) {
    this.pedidoSolicitado = { ...pedido };

    if (operation === 'confirm') this.statusConfirmed();
    else if (operation === 'decline') this.statusDeclined();
    else throw new Error('Operación no válida');
  }

  openEditElaborandose(pedido: OrderStatusEditDTO) {
    this.pedidoElaborandose = { ...pedido };

    this.statusProcessed();
  }

  statusConfirmed() {
    this.confirmationService.confirm({
      message: '¿Quieres confirmar el pedido seleccionado?',
      header: 'Confirmación de pedido',
      icon: 'pi pi-info-circle',
      accept: () => {

        if (this.pedidoSolicitado.idOrder) {
          this.pedidoSolicitado.orderStatus =
            OrderStatusEditDTO.OrderStatusEnum.Elaborandose;
          this.pedidoSolicitado.idChef = '10';
          this.restApi
            .updatePedido(this.pedidoSolicitado.idOrder, this.pedidoSolicitado)
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
        if (this.pedidoSolicitado.idOrder) {
        this.restApi.deletePedido(this.pedidoSolicitado.idOrder).subscribe((data: {}) => {
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

  statusProcessed() {
    this.confirmationService.confirm({
      message: '¿Pedido preparado?',
      header: 'Confirmación de estado',
      icon: 'pi pi-info-circle',
      accept: () => {

        if (this.pedidoElaborandose.idOrder) {
          this.pedidoElaborandose.orderStatus =
            OrderStatusEditDTO.OrderStatusEnum.Preparado;
          this.restApi
            .updatePedido(this.pedidoElaborandose.idOrder, this.pedidoElaborandose)
            .subscribe((data: {}) => {
              this.ngOnInit();
            });

          this.msgs = [
            {
              severity: 'success',
              summary: 'Confirmed',
              detail: 'Pedido preparado',
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
