import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {
  authForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.authForm = this.fb.group({
      phoneNumber: [''] // Поле для номера телефона
    });
  }

  onSubmit() {
    const formData = new HttpParams().set('phoneNumber', this.authForm.value.phoneNumber);

    this.http.post<{ status: number; message: string }>('/auth', formData, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    }).subscribe({
      next: (response) => {
        alert(response.message);
        if (response.status == 1) {
          this.router.navigate(['/code']);
        }
      },
      error: (error) => {
        console.error(error.error?.message || 'Ошибка запроса');
      }
    });
  }
}
