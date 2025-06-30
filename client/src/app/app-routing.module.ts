import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './dashboard/nav/nav.component';
import { DashComponent } from './dashboard/dash/dash.component';
import { AboutComponent } from './dashboard/about/about.component';
import { FeedbackComponent } from './dashboard/feedback/feedback.component';
import { AuthComponent } from './dashboard/auth/auth.component';

const routes: Routes = [
  {
    path: '',
    component: NavComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashComponent },
      { path: 'about', component: AboutComponent },
      { path: 'feedback', component: FeedbackComponent },
      { path: 'auth', component: AuthComponent },
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
