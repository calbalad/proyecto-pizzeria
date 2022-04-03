import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StepsDemo} from './stepsdemo';
import { StepsDemoRoutingModule } from './stepsdemo-routing.module';
import { StepsModule } from 'primeng/steps';
import { TabViewModule } from 'primeng/tabview';
import { ConfirmationDemo } from './confirmationdemo';
import { PersonalDemo } from './personaldemo';
import { PaymentDemo } from './paymentdemo';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { InputMaskModule } from 'primeng/inputmask';
import { CheckboxModule } from 'primeng/checkbox';
import { ToastModule } from 'primeng/toast';
import { FormsModule } from '@angular/forms';
import { TicketService } from './ticketservice';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {ToolbarModule} from 'primeng/toolbar';
import {ConfirmDialogModule} from 'primeng/confirmdialog';


@NgModule({
	imports: [
		CommonModule,
		StepsDemoRoutingModule,
        StepsModule,
		TabViewModule,
		ButtonModule,
		CardModule,
		InputTextModule,
		DropdownModule,
		InputMaskModule,
		CheckboxModule,
		ToastModule,
		FormsModule,
    TableModule,
    DialogModule,
    ToolbarModule,
    ConfirmDialogModule,
	],
	declarations: [
		StepsDemo,
		ConfirmationDemo,
		PersonalDemo,
		PaymentDemo
	],
	providers: [
		TicketService
	]
})
export class CartModule {}
