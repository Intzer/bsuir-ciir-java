import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RentalDurationService {

  constructor(private http: HttpClient) { }

  getRentalDurations(): Observable<any[]> {
    return this.http.get<any[]>("/api/rentaldurations");
  }
}
