import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router'
import { StepsDemo } from './stepsdemo';
import { ConfirmationDemo } from './confirmationdemo';

import { PaymentDemo } from './paymentdemo';
import { PersonalDemo } from './personaldemo';

@NgModule({
	imports: [
		RouterModule.forChild([
			{path:'',component: StepsDemo, children:[
				{path:'', redirectTo: 'carrito', pathMatch: 'full'},
				{path: 'carrito', component: PersonalDemo},
				{path: 'direcciones', component: ConfirmationDemo},
				{path: 'payment', component: PaymentDemo}
			]}
		])
	],
	exports: [
		RouterModule
	]
})
export class StepsDemoRoutingModule {}
