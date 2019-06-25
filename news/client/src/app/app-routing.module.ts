import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CanActivateAuthGuard } from './security/can-activate-auth.guard';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainComponent } from './main/main.component';
import { NewsComponent } from './news/news.component';

const routes: Routes = [
  { path: 'news/:id', component: NewsComponent/*, canActivate:[CanActivateAuthGuard]*/ },
  { path: 'main', component: MainComponent/*, canActivate:[CanActivateAuthGuard]*/ },
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
