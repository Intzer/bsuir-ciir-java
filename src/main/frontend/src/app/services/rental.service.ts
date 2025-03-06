import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  constructor(private http: HttpClient) {}

  getRentals(): Observable<any[]> {
    return this.http.get<any[]>("/api/rentals");
  }

  getHistoryRentals(): Observable<any[]> {
    return this.http.get<any[]>("/api/history");
  }
}
