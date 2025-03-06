import { Component, OnInit } from '@angular/core';
import { MotorcycleService } from '../services/motorcycle.service';
import {  CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {RentalService} from '../services/rental.service'; // Добавляем CommonModule
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-history',
  imports: [CommonModule, RouterModule],
  standalone: true, // <-- Добавь эту строку
  templateUrl: './history.component.html',
  styleUrl: './history.component.css',
  providers: [
    DatePipe,
  ]
})
export class HistoryComponent implements OnInit {

  historyRentals: any[] = [];
  isLoading = true;
  errorMessage: string | null = null;

  constructor(private rentalService: RentalService) {}

  ngOnInit(): void {
    console.log("Here")
    this.rentalService.getHistoryRentals().subscribe({
      next: (data) => {
        console.log(data);
        this.historyRentals = data;
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
