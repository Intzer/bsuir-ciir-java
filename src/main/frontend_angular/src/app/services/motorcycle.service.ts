import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MotorcycleService {
  constructor(private http: HttpClient) {}

  getMotorcycles(): Observable<any[]> {
    return this.http.get<any[]>("/api/motorcycles");
  }

  getFreeMotorcycles(): Observable<any[]> {
    return this.http.get<any[]>("/api/freemotorcycles");
  }
}
