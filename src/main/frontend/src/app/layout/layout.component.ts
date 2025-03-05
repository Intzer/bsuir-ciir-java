import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {CommonModule} from '@angular/common';
import { RouterModule} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-layout',
  standalone: true, // <-- Добавь эту строку
  imports: [RouterOutlet, RouterModule, CommonModule],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  isLogged = false;

  constructor(private authService: AuthService) {
    this.authService.isLogged$.subscribe(value => {
      this.isLogged = value; // Обновляем isLogged в шаблоне
    });
  }
}
