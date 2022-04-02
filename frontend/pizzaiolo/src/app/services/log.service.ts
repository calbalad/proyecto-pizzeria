import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginUserParam } from '../model/authService/models';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import { RouterModule, provideRoutes, Routes, ActivatedRoute, Router } from '@angular/router';
import { CartaComponent } from '../carta/carta.component';
import { NavigationExtras } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class RestApiService {

   routes: Routes  = [
    { path: 'first-component', component: CartaComponent },
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

  // HttpClient API post() method => Create PizzasCortas
  // loginUser(LoginUserParam: any): Observable<LoginUserParam> {
  //   return this.http
  //     .post<LoginUserParam>(
  //       this.apiURL + '/api/v1/auth/login',
  //       JSON.stringify(LoginUserParam),
  //       this.httpOptions
  //     )
  //     .pipe(retry(1), catchError(this.handleError));
  // }

    signIn(user: LoginUserParam) {
      return this.http
        .post<any>(`${this.apiURL}/api/v1/auth/login`, user)
        .subscribe((res: any) => {
          localStorage.setItem('access_token', res.accessToken);
          // this.getUserProfile(res._id).subscribe((res) => {
          //   this.currentUser = res;
           this.getDatos();
           this.router.navigate(['']);
          // });
        });
    }

    getDatos() {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': `Bearer ${this.getToken()}`
      })};
       let res = this.http.get(`${this.apiURL}/api/v1/users/me`, httpOptions).subscribe((res: any) => {
        localStorage.setItem('data', JSON.stringify(res));

      });

  }

    getToken() {
      return localStorage.getItem('access_token');
    }

    get isLoggedIn(): boolean {
      let authToken = localStorage.getItem('access_token');
      return authToken !== null ? true : false;
    }

    doLogout() {
      let removeToken = localStorage.removeItem('access_token');
      if (removeToken == null) {
        // this.router.navigate(['log-in']);
      }
    }

    // getUserProfile(id: any): Observable<any> {
    //   let api = `${this.apiURL}/user-profile/${id}`;
    //   return this.http.get(api, { headers: this.headers }).pipe(
    //     map((res) => {
    //       return res || {};
    //     }),
    //     catchError(this.handleError)
    //   );
    // }

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
