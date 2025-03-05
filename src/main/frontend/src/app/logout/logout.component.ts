import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import {HttpClient, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent {
  constructor(private authService: AuthService, private http: HttpClient, private router: Router) {
    this.logout();
  }

  logout() {
    this.http.get('/logout');
    this.authService.setLoggedIn(false); // Устанавливаем авторизацию
    this.router.navigate(['/auth']); // Редирект на страницу авторизации
  }
}
