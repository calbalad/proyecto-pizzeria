import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';

import {CardModule} from 'primeng/card';
import {PasswordModule} from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';

import { RouterModule } from '@angular/router';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LoginComponent, RegistroComponent
  ],
  exports: [
    LoginComponent, RegistroComponent, CardModule, PasswordModule, ButtonModule, DropdownModule, InputTextModule,FormsModule,RouterModule
  ],
  imports: [
    ReactiveFormsModule,CommonModule, CardModule, PasswordModule,ButtonModule, DropdownModule, InputTextModule,FormsModule,RouterModule
  ]
})
export class SecurityModule { }
