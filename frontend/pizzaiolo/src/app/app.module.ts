import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { PizzasComponent } from './pizzas/pizzas.component';
import { AccordionModule } from 'primeng/accordion';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';
import {DropdownModule} from 'primeng/dropdown';




@NgModule({
  declarations: [
    AppComponent,
    PizzasComponent,
  ],
  imports: [
    BrowserModule, AppRoutingModule, AccordionModule,
    ButtonModule, DataViewModule, DropdownModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
