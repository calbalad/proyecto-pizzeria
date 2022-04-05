import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PizzasCortas, OrderEditDTO } from '../model/pizzaiolo/models';
import { CreateAddressParam, UserResponse } from '../model/authService/models';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})
export class RestApiService {
  // Define API
  apiURL = 'http://localhost:8080';
  constructor(private http: HttpClient) {}
  /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
  // HttpClient API get() method => Fetch PizzasCortass list
  getPizzasCortas(): Observable<PizzasCortas> {
    return this.http
      .get<PizzasCortas>(this.apiURL + '/api/v1/pizzas')
      .pipe(retry(1), catchError(this.handleError));
  }
  getUserAuthenticated(): Observable<UserResponse> {
    var auth_token = localStorage.getItem('access_token') || '';
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${auth_token}`
    })};
    return this.http
      .get<UserResponse>(this.apiURL + '/api/v1/users/me',
      this.httpOptions)
      .pipe(retry(1), catchError(this.handleError));
  }
  getUserProfilePhoto(): Observable<any> {
    var auth_token = localStorage.getItem('access_token') || '';
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  '*/*',
        'Authorization': `Bearer ${auth_token}`
    })};
    return this.http
      .get<UserResponse>(this.apiURL + '/api/v1/users/me',
      this.httpOptions)
      .pipe(retry(1), catchError(this.handleError));
  }
  // HttpClient API get() method => Fetch PizzasCortas
  getPizzasDetalladas(id: any): Observable<any> {
    return this.http
      .get<any>(this.apiURL + '/api/v1/pizzas/'+id+'?mode=details')
      .pipe(retry(1), catchError(this.handleError));
  }
  // HttpClient API post() method => Create PizzasCortas
  createPizzasCortas(PizzasCortas: any): Observable<PizzasCortas> {
    return this.http
      .post<PizzasCortas>(
        this.apiURL + '/PizzasCortass',
        JSON.stringify(PizzasCortas),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }
  createOrder(order: any): Observable<any> {
    return this.http
      .post<any>(
        this.apiURL + '/api/v1/pedidos',
        JSON.stringify(order),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }
  createAddress(CreateAddressParam: any, id: string): Observable<CreateAddressParam> {
    var auth_token = localStorage.getItem('access_token') || '';
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${auth_token}`
    })};
    return this.http
      .post<CreateAddressParam>(
        this.apiURL + '/api/v1/address/' + id,
        JSON.stringify(CreateAddressParam),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  createCommet(commet: any, idPizza: any, idUsuario: any): Observable<any> {
    return this.http
      .post<any>(
        this.apiURL + '/api/v1/comentarios',
        JSON.stringify({
  "comentario": commet,
  "date": "2022-04-05T19:18:39.619Z",
  "idComentario": 0,
  "idPizza": idPizza,
  "idUsuario": idUsuario,
  "rating": 0
}),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  uploadFile(file: any, id: string): Observable<any> {
    var auth_token = localStorage.getItem('access_token') || '';
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${auth_token}`
    })};
    return this.http
      .post<any>(
        this.apiURL + '/api/v1/users/'+id+'/picture?action=u',
        file,
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API put() method => Update PizzasCortas
  updatePizzasCortas(id: any, PizzasCortas: any): Observable<PizzasCortas> {
    return this.http
      .put<PizzasCortas>(
        this.apiURL + '/PizzasCortass/' + id,
        JSON.stringify(PizzasCortas),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  updateUser( user: any): Observable<any> {
    var auth_token = localStorage.getItem('access_token') || '';
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${auth_token}`
    })};
    return this.http
      .put<any>(
        this.apiURL + '/api/v1/users/' + user.id,
        JSON.stringify(user),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }
  // HttpClient API delete() method => Delete PizzasCortas
  deletePizzasCortas(id: any) {
    return this.http
      .delete<PizzasCortas>(this.apiURL + '/PizzasCortass/' + id, this.httpOptions)
      .pipe(retry(1), catchError(this.handleError));
  }

  resetPassword(email: any): Observable<any> {
    return this.http
      .post<any>(
        this.apiURL + '/api/v1/auth/forgot-password',
        {"email": email},
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
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
