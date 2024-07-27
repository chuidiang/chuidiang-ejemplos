import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, EMPTY, Observable } from 'rxjs';

/**
 * Servicio con el HttpClient para hacer llamadas.
 * Para que HttpClient esté accesible, es necesario en app.config.ts, en providers,
 * poner un provideHttpClient(). 
 */
@Injectable({
  providedIn: 'root',
})
export class DataService {
  private apiUrl = 'http://localhost:5000/users';

  /**
   * Recbie por inyección el HttpClient para poder hacer llamadas REST al servidor.
   * @param http 
   */
  constructor(private http: HttpClient) { }

  // GET request
  getItems(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  // POST request
  addItem(item: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, item);
  }

  // PUT request
  updateItem(id: number, item: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, item);
  }

  // DELETE request
  deleteItem(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`).pipe(
      catchError((error:HttpErrorResponse) => {
        console.log("Error en servicio "+error.message);
        return EMPTY;
        // return throwError(()=> new Error("Error en borrado"));
      })
    );
  }
}