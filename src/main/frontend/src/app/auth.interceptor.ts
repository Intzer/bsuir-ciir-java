import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import {AuthService} from './services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router); // Инжектим Router вручную
  const authService = inject(AuthService); // Инжектим Router вручную

  return next(req).pipe(
    catchError((error) => {
      if (error.status === 401) {
        authService.setLoggedIn(false); // Разлогиниваем
        router.navigate(['/auth']); // Редирект при истекшей сессии
      }
      return throwError(() => error);
    })
  );
};
