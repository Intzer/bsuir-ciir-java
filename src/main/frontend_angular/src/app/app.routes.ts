import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component'
import { HomeComponent } from './home/home.component';
import { AuthComponent } from './auth/auth.component';
import {CodeComponent} from './code/code.component';
import {CabinetComponent} from './cabinet/cabinet.component';
import {LogoutComponent} from './logout/logout.component';
import {ActiveRentsComponent} from './active-rents/active-rents.component';
import {RentComponent} from './rent/rent.component';
import {HistoryComponent} from './history/history.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'auth', component: AuthComponent },
      { path: 'code', component: CodeComponent },
      { path: 'cabinet', component: CabinetComponent },
      { path: 'logout', component: LogoutComponent },
      { path: 'rent', component: RentComponent },
      { path: 'active', component: ActiveRentsComponent },
      { path: 'history', component: HistoryComponent },
    ]
  }
];
