import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedSubject = new BehaviorSubject<boolean>(localStorage.getItem('isLogged') === 'true');
  isLogged$ = this.isLoggedSubject.asObservable();

  setLoggedIn(value: boolean) {
    localStorage.setItem('isLogged', value.toString());
    this.isLoggedSubject.next(value); // Обновляем подписчиков
  }
}
