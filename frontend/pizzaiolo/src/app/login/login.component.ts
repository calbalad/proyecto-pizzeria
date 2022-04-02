import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../services/log.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  signinForm: FormGroup;
  constructor(
    public fb: FormBuilder,
    public authService: LoginService,
    public router: Router
  ) {
    this.signinForm = this.fb.group({
      email: [''],
      password: [''],
    });
  }
  ngOnInit() {}
  loginUser() {
    this.authService.signIn(this.signinForm.value);
  }
  getData(){
    this.authService.getDatos();
  }
  logout() {
    this.authService.doLogout();
  }
}
