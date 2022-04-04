import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterParam } from '../model/authService/models';
import { Head, Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import { RouterModule, provideRoutes, Routes, ActivatedRoute, Router } from '@angular/router';
import { CartaComponent } from '../carta/carta.component';
import { HeaderComponent } from '../main/header/header.component';
import { NavigationExtras } from '@angular/router';
import { Password } from 'primeng/password';


@Injectable({
  providedIn: 'root',
})
export class RegistroService {

   routes: Routes  = [
    { path: 'first-component', component: CartaComponent },{component: HeaderComponent}
  ];
  // Define API
  apiURL = 'http://localhost:8080';
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}
  /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

    register(user: RegisterParam) {
       this.http
        .post<any>(`${this.apiURL}/api/v1/auth/register`, user).pipe(catchError(this.handleError)).subscribe((res: any) => {
           return this.router.navigateByUrl('/login');
        });

    }

  // Error handling
  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
