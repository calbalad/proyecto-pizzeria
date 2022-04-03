import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { PizzasEditable } from '../model/pizzaiolo/pizzasEditable';
import { PizzasEditables } from '../model/pizzaiolo/pizzasEditables';

@Injectable({
  providedIn: 'root'
})
export class PizzasService {
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
  getPizzas(): Observable<PizzasEditables> {
    return this.http
      .get<PizzasEditables>(this.apiURL + '/api/v1/pizzas')
      .pipe(retry(1), catchError(this.handleError));
  }

  getPizza(idPizza: number): Observable<PizzasEditables> {
    return this.http
      .get<PizzasEditables>(this.apiURL + '/api/v1/pizzas/' + idPizza + '?mode=edit')
      .pipe(retry(1), catchError(this.handleError));
  }

  createPizza(pizza: PizzasEditables): Observable<PizzasEditables> {
    console.log( JSON.stringify(pizza))
    return this.http
      .post<PizzasEditables>(
        this.apiURL + '/api/v1/pizzas',
        JSON.stringify(pizza),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API put() method => Update PizzasCortas
  updatePizza(id: number, pizza: PizzasEditables): Observable<PizzasEditables> {
    return this.http
      .put<PizzasEditables>(
        this.apiURL + '/api/v1/pizzas/' + id,
        JSON.stringify(pizza),
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
