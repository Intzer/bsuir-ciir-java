import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-code',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './code.component.html',
  styleUrl: './code.component.css'
})
export class CodeComponent {
  codeForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router, private authService: AuthService) {
    this.codeForm = this.fb.group({
      code: [''],
    });
  }

  onSubmit(action: string) {
    const formData = new HttpParams()
      .set('code', this.codeForm.value.code)
      .set('action', action);

    console.log(this.codeForm.value.code, this.codeForm.value.action);

    this.http.post<{ status: number; message: string }>('/code', formData, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    }).subscribe({
      next: (response) => {
        if (action === "enter" && response.status === 1) {
          this.authService.setLoggedIn(true); // Устанавливаем авторизацию
          this.router.navigate(['/cabinet']);
        } else {
          alert(response.message);
        }
      },
      error: (response) => {
        alert(response.message);
      },
    });
  }
}
