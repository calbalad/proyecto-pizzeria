import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IngredientesEditables } from '../model/pizzaiolo/models';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IngredientesService {

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
  getIngredientes(): Observable<IngredientesEditables> {
    return this.http
      .get<IngredientesEditables>(this.apiURL + '/api/v1/ingredientes')
      .pipe(retry(1), catchError(this.handleError));
  }

  createIngrediente(ingrediente: IngredientesEditables): Observable<IngredientesEditables> {
    console.log( JSON.stringify(ingrediente))
    return this.http
      .post<IngredientesEditables>(
        this.apiURL + '/api/v1/ingredientes',
        JSON.stringify(ingrediente),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API put() method => Update PizzasCortas
  updateIngrediente(id: number, IngredientesEditables: IngredientesEditables): Observable<IngredientesEditables> {
    return this.http
      .put<IngredientesEditables>(
        this.apiURL + '/api/v1/ingredientes/' + id,
        JSON.stringify(IngredientesEditables),
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
