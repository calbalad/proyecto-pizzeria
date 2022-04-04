import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserEditComponent, UserResetComponent } from './user.component';

import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';
import { AppRoutingModule } from '../app-routing.module';
import { FormsModule } from '@angular/forms';
import {FileUploadModule} from 'primeng/fileupload';
import {HttpClientModule} from '@angular/common/http';



@NgModule({
  declarations: [
    UserEditComponent, UserResetComponent,
  ],
  imports: [
    CommonModule, InputTextModule, DropdownModule, ButtonModule, PasswordModule,
    CardModule,ButtonModule, AppRoutingModule, FormsModule, FileUploadModule, HttpClientModule
  ]
})
export class UserModule { }
