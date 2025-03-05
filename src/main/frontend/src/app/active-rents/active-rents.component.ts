import { Component, OnInit } from '@angular/core';
import { MotorcycleService } from '../services/motorcycle.service';
import {  CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {RentalService} from '../services/rental.service'; // Добавляем CommonModule

@Component({
  selector: 'app-active-rents',
  imports: [CommonModule, RouterModule],
  standalone: true, // <-- Добавь эту строку
  templateUrl: './active-rents.component.html',
  styleUrl: './active-rents.component.css'
})
export class ActiveRentsComponent implements OnInit {

  rentals: any[] = [];
  isLoading = true;
  errorMessage: string | null = null;

  constructor(private rentalService: RentalService) {}

  ngOnInit(): void {
    console.log("Here")
    this.rentalService.getRentals().subscribe({
      next: (data) => {
        console.log(data);
        this.rentals = data;
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
