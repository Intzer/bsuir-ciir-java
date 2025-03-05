import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-cabinet',
  standalone: true,
  imports: [],
  templateUrl: './cabinet.component.html',
  styleUrls: ['./cabinet.component.css']
})
export class CabinetComponent implements OnInit {
  userInfo: any = {}; // Объект для хранения информации о пользователе

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Делаем GET-запрос непосредственно в компоненте
    this.http.get<any>('/info').subscribe({
      next: (data) => {
        this.userInfo = data; // Сохраняем полученные данные в переменную
      },
      error: (err) => {
        console.error('Ошибка при получении данных:', err);
      }
    });
  }
}
