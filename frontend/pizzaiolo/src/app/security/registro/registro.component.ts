import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from 'src/app/common-service/notification.service';
import { SecurityModule } from '../security.module';
import { User } from '../../model/authService/models';
import { RegistroService, } from 'src/app/services/registro.service';
import {CardModule} from 'primeng/card';
import {PasswordModule} from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { FormGroupDirective, FormControlDirective } from '@angular/forms'
import { DATE_PIPE_DEFAULT_TIMEZONE } from '@angular/common';
@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss']
})

export class RegistroComponent implements OnInit {

  signupForm: FormGroup;
  constructor(
    public fb: FormBuilder,
    public authService: RegistroService,
    public router: Router
  ) {
    this.signupForm = this.fb.group({
      firstName : [''],
      lastName : [''],
      phone : [''],
      email: [''],
      password: [''],
      confirmPassword: [''],
      timezone: [Intl.DateTimeFormat().resolvedOptions().timeZone],
      enabled: true,
      confirmed: true,
    });
  }
  ngOnInit() {}
  register() {
    this.authService.register(this.signupForm.value);
  }
  // form: any = {
  //   nombre: null,
  //   apellidos: null,
  //   telefono: null,
  //   email: null,
  //   password: null
  // };
  // isSuccessful = false;
  // isSignUpFailed = false;
  // errorMessage = '';
  // constructor(private authService: RegistroService) { }
  // ngOnInit(): void {
  // }
  // onSubmit():void {
  //   const { nombre, apellidos,telefono, email, password } = this.form;
  //   this.authService.register(this.form).subscribe({
  //     next: (data: any) => {
  //       console.log(data);
  //       this.isSuccessful = true;
  //       this.isSignUpFailed = false;
  //     },
  //     error: (err: { error: { message: string; }; }) => {
  //       this.errorMessage = err.error.message;
  //       this.isSignUpFailed = true;
  //     }
  //   });
  // }
  }
