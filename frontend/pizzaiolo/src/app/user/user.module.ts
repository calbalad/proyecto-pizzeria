import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserEditComponent, UserResetComponent } from './user.component';

import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';


@NgModule({
  declarations: [
    UserEditComponent, UserResetComponent,
  ],
  imports: [
    CommonModule, InputTextModule, DropdownModule, ButtonModule, PasswordModule,
    CardModule
  ],
  exports: [
    CardModule, PasswordModule, ButtonModule, DropdownModule, InputTextModule,
    UserEditComponent, UserResetComponent,
  ]
})
export class UserModule { }
