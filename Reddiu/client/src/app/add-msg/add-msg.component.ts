import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message.service';
import { Router } from '@angular/router';
import { CategoryService } from '../services/category.service';
import { Category, MessageC } from '../common.models';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-add-msg',
  templateUrl: './add-msg.component.html',
  styleUrls: ['./add-msg.component.css']
})
export class AddMsgComponent implements OnInit {


  categories: Category[];
  post: MessageC;
  username: string;

  constructor(private msgService: MessageService,
              private router: Router,
              private catService: CategoryService,
              private auth: AuthenticationService) {

                this.resetPost();

               }

  ngOnInit() {
    this.getCategories();
    this.getUsername();
  }

  getCategories(){
    this.catService.getCategories().subscribe(
      (res: Category[]) => {
        this.categories = res;
      }
    );
  }

  getUsername() {
    this.username = this.auth.getCurrentUser().username;

    //console.log(this.username);
  }

  resetPost() {
    this.post = {
      title: '',
      text: '',
      user: this.username
    }
  }

  addPost(){
    this.msgService.createMessage(this.post).subscribe(
      () => {
        this.router.navigate(['/main']);
        // OR
        // since the response is the msg dto
        // it could redirect to that partcular, newly-made post
        // instead of to the main page
      }
    );
  }

}
