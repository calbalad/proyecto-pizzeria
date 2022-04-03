import {Component,OnInit} from '@angular/core';
import { TicketService } from './ticketservice';
import { Router } from '@angular/router';


@Component({
    template: `
        <div class="stepsdemo-content">
            <p-card>
                <ng-template pTemplate="title">
                    Resumen de pedido
                </ng-template>

            </p-card>
        </div>
    `,
})
export class PaymentDemo implements OnInit {

    paymentInformation: any;

    constructor(public ticketService: TicketService, private router: Router) { }

    ngOnInit() {
        this.paymentInformation = this.ticketService.ticketInformation.paymentInformation;
    }

    nextPage() {
        this.ticketService.ticketInformation.paymentInformation = this.paymentInformation;
        this.router.navigate(['carrito/payment']);
    }

    prevPage() {
        this.router.navigate(['carrito/seat']);
    }
}
