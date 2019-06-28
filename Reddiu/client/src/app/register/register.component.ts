import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../security/authentication.service';
import { NewUser } from '../common.models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  roles: any[]; // it can't parse a String array list??
  registerUser: NewUser;

  constructor(private auth: AuthenticationService,
              private router: Router) {
    this.registerUser = {
      firstName: '',
      lastName: '',
      username: '',
      password: '',
      roles: ['ROLE_USER']
    };
   }

  ngOnInit() {
    this.getRoles();
  }

  getRoles() {
    this.auth.getRoles().subscribe(
      (res: any[]) => {
        this.roles = res;
      }
    );
  }

  register() {
    this.auth.register(this.registerUser).subscribe(
      () => {
        this.router.navigate(['/login']);
      }
    );
  }


}
