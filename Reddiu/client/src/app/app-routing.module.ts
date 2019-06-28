import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CanActivateAuthGuard } from './security/can-activate-auth.guard';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainComponent } from './main/main.component';
import { MessageComponent } from './message/message.component';
import { AddMsgComponent } from './add-msg/add-msg.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: 'register', component: RegisterComponent},
  { path: 'post', component: AddMsgComponent},
  { path: 'message/:id', component: MessageComponent, canActivate:[CanActivateAuthGuard] },
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
