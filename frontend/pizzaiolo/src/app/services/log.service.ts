import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginUserParam } from '../model/authService/models';
import { Head, Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import { RouterModule, provideRoutes, Routes, ActivatedRoute, Router } from '@angular/router';
import { CartaComponent } from '../carta/carta.component';
import { HeaderComponent } from '../main/header/header.component';
import { NavigationExtras } from '@angular/router';
import { coerceStringArray } from '@angular/cdk/coercion';


@Injectable({
  providedIn: 'root',
})
export class LoginService {
    rol : any = '';
   routes: Routes  = [
    { path: 'first-component', component: CartaComponent },{component: HeaderComponent}
  ];
  // Define API
  apiURL = 'http://localhost:8080';
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, ) {
  }
  /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

    signIn(user: LoginUserParam) {
      return this.http
        .post<any>(`${this.apiURL}/api/v1/auth/login`, user)
        .subscribe((res: any) => {
          localStorage.setItem('access_token', res.accessToken);
          console.log("Entra login")
           this.getDatos();
           console.log("Termina login")

        });
    }

    getDatos() {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Bearer ${this.getToken()}`
      })};
      console.log("Entra get Datos")
       let res = this.http.get(`${this.apiURL}/api/v1/users/me`, httpOptions).subscribe((res: any) => {
        localStorage.setItem('data', JSON.stringify(res));
        console.log("Entra get datos y success")
        this.router.navigateByUrl('/');
        window.location.replace('/');

      });

  }

    getToken() {
      return localStorage.getItem('access_token');
    }

    getRole() {
      this.rol = JSON.parse(localStorage.getItem('data') || '[]');
      //console.log(this.rol.data.role.name)
      return this.rol.data.role.name;
    }

    isLoggedIn(): boolean {
      let authToken = localStorage.getItem('access_token');
      return authToken !== null ? true : false;
    }

    doLogout() {
      let removeToken = localStorage.removeItem('access_token');
      let removeData = localStorage.removeItem('data');
      let removeCart = localStorage.removeItem('cart');
      if (removeToken == null && removeData == null && removeCart == null) {
        this.router.navigate(['/']);
        window.location.replace('/');
      }
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
    return throwError(() => {
      return errorMessage;
    });
  }
}
