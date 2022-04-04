import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class TicketService {

    ticketInformation = {
        personalInformation: {
          address: "",
          amount: 0,
          comment: "",
          idOrder: 0,
          idUser: "",
          orderDate: "",
          pizzas: [{}]
        },
        seatInformation: {
        },
        paymentInformation: {
        }
    };

    private paymentComplete = new Subject<any>();

    paymentComplete$ = this.paymentComplete.asObservable();

    getTicketInformation() {
        return this.ticketInformation;
    }

    setTicketInformation(ticketInformation: any) {
        this.ticketInformation = ticketInformation;
    }

    complete() {
        this.paymentComplete.next(this.ticketInformation.personalInformation);
    }
}
