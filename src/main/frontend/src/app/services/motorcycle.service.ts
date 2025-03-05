import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MotorcycleService {
  private apiUrl = 'http://127.0.0.1:8080/api/motorcycles';
  private apiUrlFree = 'http://127.0.0.1:8080/api/freemotorcycles';

  constructor(private http: HttpClient) {}

  getMotorcycles(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getFreeMotorcycles(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlFree);
  }
}
