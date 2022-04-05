import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OrderDetailsDTO, OrderStatusEditDTO } from '../model/pizzaiolo/models';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';


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
  getPedidosSolicitados(): Observable<OrderDetailsDTO> {
    return this.http
      .get<OrderDetailsDTO>(this.apiURL + '/api/v1/pedidos/solicitado')
      .pipe(retry(1), catchError(this.handleError));
  }
  // HttpClient API get() method => Fetch PedidosElaborandose list
  getPedidosElaborandose(): Observable<OrderDetailsDTO> {
    return this.http
      .get<OrderDetailsDTO>(this.apiURL + '/api/v1/pedidos/elaborandose')
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API get() method => Fetch PedidosPreparados list
  getPedidosPreparados(): Observable<OrderDetailsDTO> {
    return this.http
      .get<OrderDetailsDTO>(this.apiURL + '/api/v1/pedidos/preparado')
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API get() method => Fetch PedidosEnviados list
  getPedidosEnviados(): Observable<OrderDetailsDTO> {
    return this.http
      .get<OrderDetailsDTO>(this.apiURL + '/api/v1/pedidos/enviado')
      .pipe(retry(1), catchError(this.handleError));
  }

  getPedidosUser(id: string): Observable<any> {
    return this.http
      .get<any>(this.apiURL + '/api/v1/pedidos/list/' + id)
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API get() method => Fetch PedidosRecibidos list
  getPedidosRecibidos(): Observable<OrderDetailsDTO> {
    return this.http
      .get<OrderDetailsDTO>(this.apiURL + '/api/v1/pedidos/recibido')
      .pipe(retry(1), catchError(this.handleError));
  }

  // HttpClient API put() method => Update PedidosEditables
  updatePedido(
    id: number,
    OrderStatusEditDTO: OrderStatusEditDTO
  ): Observable<OrderStatusEditDTO> {
    return this.http
      .put<OrderStatusEditDTO>(
        this.apiURL + '/api/v1/pedidos/' + id,
        JSON.stringify(OrderStatusEditDTO),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  deletePedido(id: number): Observable<any> {

    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        idOrder: id
      },
    };

    return this.http
      .delete<any>(this.apiURL + '/api/v1/pedidos/' + id, options)
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
