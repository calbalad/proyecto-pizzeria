import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PizzasCortas, OrderEditDTO } from '../model/pizzaiolo/models';
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
  createOrder(OrderEditDTO: any): Observable<OrderEditDTO> {
    return this.http
      .post<OrderEditDTO>(
        this.apiURL + '/api/v1/pedidos',
        JSON.stringify(OrderEditDTO),
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
  // HttpClient API delete() method => Delete PizzasCortas
  deletePizzasCortas(id: any) {
    return this.http
      .delete<PizzasCortas>(this.apiURL + '/PizzasCortass/' + id, this.httpOptions)
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
