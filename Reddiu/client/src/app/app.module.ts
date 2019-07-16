import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationService } from './security/authentication.service';
import { CanActivateAuthGuard } from './security/can-activate-auth.guard';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './security/token-interceptor.service';
import { JwtUtilsService } from './security/jwt-utils.service';
import { MainComponent } from './main/main.component';
import { CategoryService } from './services/category.service';
import { MessageService } from './services/message.service';
import { CommentService } from './services/comment.service';
import { MessageComponent } from './message/message.component';
import { CommentComponent } from './comment/comment.component';
import { AddMsgComponent } from './add-msg/add-msg.component';
import { AddCommentComponent } from './add-comment/add-comment.component';
import { RegisterComponent } from './register/register.component';
import { VoteService } from './services/vote.service';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    MainComponent,
    MessageComponent,
    CommentComponent,
    AddMsgComponent,
    AddCommentComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    AuthenticationService,
    CanActivateAuthGuard,
    JwtUtilsService,
    CategoryService,
    MessageService,
    CommentService,
    VoteService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
