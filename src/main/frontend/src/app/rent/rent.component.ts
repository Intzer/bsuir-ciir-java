import { Component, OnInit } from '@angular/core';
import { MotorcycleService } from '../services/motorcycle.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-rent',
  standalone: true,
  imports: [
    ReactiveFormsModule, CommonModule
  ],
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css'],
})
export class RentComponent implements OnInit {
  rentForm: FormGroup;
  motorcycles: any[] = [];
  isLoading = true;
  errorMessage: string | null = null;
  motorcycleId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private motorcycleService: MotorcycleService
  ) {
    this.rentForm = this.fb.group({
      motorcycleId: [0, Validators.required],   // Поле для ID мотоцикла
      rentalDuration: [1, Validators.required], // Поле для длительности аренды
      rentalDate: ['', Validators.required],    // Поле для даты аренды
    });
  }

  openModal(motorcycleId: number) {
    console.log("open");
    this.motorcycleId = motorcycleId;
    this.rentForm.patchValue({ motorcycleId: this.motorcycleId }); // Заполняем форму ID
  }

  onSubmitPost() {
    if (this.rentForm.invalid) {
      return;
    }

    const { motorcycleId, rentalDuration, rentalDate } = this.rentForm.value;
    const formData = new HttpParams()
      .set('motorcycleId', motorcycleId.toString())
      .set('rentalDuration', rentalDuration.toString())
      .set('rentalDate', rentalDate);

    this.http.post<{ status: number; message: string }>('/rent', formData, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
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
    this.motorcycleService.getFreeMotorcycles().subscribe({
      next: (data) => {
        this.motorcycles = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Ошибка при загрузке мотоциклов', err);
        this.errorMessage = 'Не удалось загрузить данные.';
        this.isLoading = false;
      },
    });
  }
}
