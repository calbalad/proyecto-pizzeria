import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartaComponent } from './carta/carta.component';
import { MainModule } from './main';
import { CartaProductComponent } from './cartaProduct/cartaProduct.component';
import { CarritoComponent } from './carrito/carrito.component';

import { AccordionModule } from 'primeng/accordion';
import { MenuModule } from 'primeng/menu';
import { DataViewModule } from 'primeng/dataview';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { RatingModule } from 'primeng/rating';
import { RippleModule } from 'primeng/ripple';
import {CarouselModule} from 'primeng/carousel';
import {GalleriaModule} from 'primeng/galleria';
import {InputNumberModule} from 'primeng/inputnumber';
import {PasswordModule} from 'primeng/password';
import {DividerModule} from 'primeng/divider';
import {CardModule} from 'primeng/card';
import { LoginComponent } from './login/login.component';
import {OrderListModule} from 'primeng/orderlist';
import {ImageModule} from 'primeng/image';
import { PedidosComponent } from './pedidos/pedidos.component';
import {FieldsetModule} from 'primeng/fieldset';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NoopAnimationsModule} from '@angular/platform-browser/animations'




@NgModule({

  declarations: [AppComponent, PizzaDetailComponent, LoginComponent, CarritoComponent, CartaComponent, PedidosComponent,],

  imports: [
    BrowserModule,
    AppRoutingModule,
    AccordionModule,
    ButtonModule,
    DataViewModule,
    DropdownModule,
    MainModule,
    MenuModule,
    RippleModule,
    RatingModule,
    InputTextModule,
    DialogModule,
    PanelModule,
    CarouselModule,
    GalleriaModule,
    InputNumberModule,
    PasswordModule,
    DividerModule,
    CardModule,
    OrderListModule,
    ImageModule,
    FieldsetModule,
    BrowserAnimationsModule,
    NoopAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
