import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OrderEditDTO, OrderShortDTO } from '../model/pizzaiolo/models';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class PedidosService {
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

  // HttpClient API get() method => Fetch PedidosSolicitados list
  getPedidosSolicitados(): Observable<OrderShortDTO> {
    return this.http
      .get<OrderShortDTO>(this.apiURL + '/api/v1/pedidos/solicitado')
      .pipe(retry(1), catchError(this.handleError));
  }
  // HttpClient API get() method => Fetch PedidosSolicitados list
  getPedidosElaborandose(): Observable<OrderShortDTO> {
    return this.http
      .get<OrderShortDTO>(this.apiURL + '/api/v1/pedidos/elaborandose')
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API put() method => Update PedidosEditables
  updatePedido(
    id: number,
    OrderEditDTO: OrderEditDTO
  ): Observable<OrderEditDTO> {
    return this.http
      .put<OrderEditDTO>(
        this.apiURL + '/api/v1/pedidos' + id,
        JSON.stringify(OrderEditDTO),
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
