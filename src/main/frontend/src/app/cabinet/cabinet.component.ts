import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

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

  deposit() {
    let amount = Number(prompt('Введите сумму пополнения: ', ''));
    if (!amount) {
      return;
    }

    const formData = new HttpParams()
      .set('amount', amount);

    this.http.post<{ status: number; message: string }>('/deposit', formData).subscribe({
      next: (response) => {
        if (response.status == 1) {
          this.userInfo.balance += amount;
        }

        alert(response.message);
      }
    });
  }

  ngOnInit() {
    this.http.get<any>('/info').subscribe({
      next: (data) => {
        this.userInfo = data; // Сохраняем полученные данные в переменную
      }
    });
  }
}
