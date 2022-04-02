import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CocinaComponent, RepartoComponent } from './tienda.component';



@NgModule({
  declarations: [
    CocinaComponent, RepartoComponent
  ],
  imports: [
    CommonModule
  ]
})
export class TiendaModule { }
