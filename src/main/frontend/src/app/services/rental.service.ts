import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  private apiUrl = 'http://127.0.0.1:8080/api/rentals';
  private apiUrlHistory = 'http://127.0.0.1:8080/api/history';

  constructor(private http: HttpClient) {}

  getRentals(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getHistoryRentals(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlHistory);
  }
}
