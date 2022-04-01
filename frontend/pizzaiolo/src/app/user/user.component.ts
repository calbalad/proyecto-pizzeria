import { Component, OnInit } from '@angular/core';
import {UserModule} from '../user';

@Component({
  selector: 'app-user-edit',
  templateUrl: './tmpl-edit.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserEditComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
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
