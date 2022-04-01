import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';

import {CardModule} from '../../../node_modules/primeng/card';
import {PasswordModule} from '../../../node_modules/primeng/password/password';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';


@NgModule({
  declarations: [
    LoginComponent, RegistroComponent
  ],
  exports: [
    LoginComponent, RegistroComponent, CardModule, PasswordModule, ButtonModule, DropdownModule, InputTextModule
  ],
  imports: [
    CommonModule, CardModule, PasswordModule,ButtonModule, DropdownModule, InputTextModule,
  ]
})
export class SecurityModule { }
