import { Component, OnInit } from '@angular/core';
import { MotorcycleService } from '../services/motorcycle.service';
import {  CommonModule } from '@angular/common';
import {Router, RouterModule} from '@angular/router';
import {RentalService} from '../services/rental.service';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http'; // Добавляем CommonModule

@Component({
  selector: 'app-rent',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  standalone: true, // <-- Добавь эту строку
  templateUrl: './rent.component.html',
  styleUrl: './rent.component.css'
})
export class RentComponent implements OnInit {
  rentForm: FormGroup;
  motorcycles: any[] = [];
  isLoading = true;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private motorcycleService: MotorcycleService) {
    this.rentForm = this.fb.group({
      motorcycleId: [0],
    });
  }

  onSubmit(motorcycleId: number) {
    const formData = new HttpParams()
      .set('motorcycleId', motorcycleId);

    this.http.post<{ status: number; message: string }>('/rent', formData, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    }).subscribe({
      next: (response) => {
          alert(response.message);
          if (response.status == 1) {
            this.router.navigate(['/active']);
          }
      },
      error: (response) => {
        alert(response.message);
      },
    });
  }

  ngOnInit(): void {
    console.log("Here")
    this.motorcycleService.getFreeMotorcycles().subscribe({
      next: (data) => {
        console.log(data);
        this.motorcycles = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Ошибка при загрузке мотоциклов', err);
        this.errorMessage = 'Не удалось загрузить данные.';
        this.isLoading = false;
      }
    });
  }
}
