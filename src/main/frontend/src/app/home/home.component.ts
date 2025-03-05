import { Component, OnInit } from '@angular/core';
import { MotorcycleService } from '../services/motorcycle.service';
import {  CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Добавляем CommonModule

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterModule],
  standalone: true, // <-- Добавь эту строку
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  motorcycles: any[] = [];
  isLoading = true;
  errorMessage: string | null = null;

  constructor(private motorcycleService: MotorcycleService) {}

  ngOnInit(): void {
    this.motorcycleService.getMotorcycles().subscribe({
      next: (data) => {
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
