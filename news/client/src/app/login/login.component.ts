import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { AuthenticationService } from '../security/authentication.service'
import { Observable } from 'rxjs';

import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  public user;

  public wrongUsernameOrPass:boolean;

  public alertText: string;

  constructor(private authenticationService:AuthenticationService,
              private router: Router) {
    this.user = {};
    this.wrongUsernameOrPass = false;
   }

  ngOnInit() {
  }

  login():void{
    this.authenticationService.login(this.user.username, this.user.password).subscribe(
      (loggedIn:boolean) => {
        if(loggedIn){
          this.wrongUsernameOrPass = false;
          this.router.navigate(['/main']);
        } else {
          this.wrongUsernameOrPass = true;
          this.showAlertText(); // it's still not loading it...
        }

      }
    );
  }

  showAlertText(){
    this.alertText = "Wrong username or password."
  }


}
