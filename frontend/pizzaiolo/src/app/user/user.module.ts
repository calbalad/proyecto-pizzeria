import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserEditComponent, UserResetComponent } from './user.component';



@NgModule({
  declarations: [
    UserEditComponent, UserResetComponent
  ],
  imports: [
    CommonModule
  ],
  exports: []
})
export class UserModule { }
