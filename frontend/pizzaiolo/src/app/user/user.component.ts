import { Component, OnInit } from '@angular/core';
import { User } from '../model/authService/user';
import { UserResponse } from '../model/authService/userResponse';
import {UserModule} from '../user';

@Component({
  selector: 'app-user-edit',
  templateUrl: './tmpl-edit.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserEditComponent implements OnInit {
  test: string = "";
  user: any = [];

  constructor() {
    this.user = JSON.parse(localStorage.getItem('data') || '{}');
    this.user = this.user.data
    console.log(this.user)

  }

  ngOnInit(): void {

  }

  saveUser() {
    console.log(this.test)
  }

}

@Component({
  selector: 'app-user-reset',
  templateUrl: './tmpl-reset.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserResetComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
