import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IngredientesEditables } from '../model/pizzaiolo/models';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { UserListResponse } from '../model/authService/userListResponse';
import { UserResponse } from '../model/authService/userResponse';
import { LoginService } from './log.service';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  apiURL = 'http://localhost:8080';
  constructor(private http: HttpClient) {}
  /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/
  // Http Options
  token = LoginService.prototype.getToken();
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token}`
    }),
  };

  // HttpClient API get() method => Fetch PizzasCortass list
  getUsuarios(): Observable<UserListResponse> {
    return this.http
      .get<UserListResponse>(this.apiURL + '/api/v1/users', this.httpOptions)
      .pipe(retry(1), catchError(this.handleError));
  }


  // HttpClient API put() method => Update PizzasCortas
  updateUsuario(id: string, body: any): Observable<any> {
    return this.http
      .put<UserResponse>(
        this.apiURL + '/api/v1/users/' + id +'/role',
        JSON.stringify(body),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

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
