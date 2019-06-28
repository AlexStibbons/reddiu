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

  roles: any[];
  registerUser: NewUser;

  // helper
  addRole: string;

  constructor(private auth: AuthenticationService,
              private router: Router) {
    this.registerUser = {
      firstName: '',
      lastName: '',
      username: '',
      password: '',
      //roles: ['ROLE_USER']
      roles: []
    };
    this.addRole = '';
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
    this.registerUser.roles.push(this.addRole);
    this.auth.register(this.registerUser).subscribe(
      (res: NewUser) => {
        console.log(res);
        this.router.navigate(['/login']);
      }
    );
  }


}
