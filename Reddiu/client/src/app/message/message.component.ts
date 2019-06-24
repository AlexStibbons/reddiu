import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message.service';
import { ActivatedRoute } from '@angular/router';
import { MessageC, CommentC, Category } from '../common.models';
import { CommentService } from '../services/comment.service';
import { AuthenticationService } from '../security/authentication.service';
import { CategoryService } from '../services/category.service';


@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  id: number;
  private sub: any;
  message: MessageC;

  // for add comment
  parentComment: CommentC;

  // helpers
  isDeleted: boolean;

  // editing helper
  editing = false;
  categories: Category[];

  constructor(private msgService: MessageService,
              private route: ActivatedRoute,
              private commSer: CommentService,
              private auth: AuthenticationService,
              private categoryService: CategoryService) { }

  ngOnInit() {
    this.getMessage();
    this.resetParentComment();
    this.getCategories();
  }

  getMessage(){
    this.sub = this.route.params.subscribe( params =>
      {
        this.id = params.id;
        this.msgService.getMessageById(this.id).subscribe(
          (res: MessageC) => {
            this.message = res;
          }
        );
      }
    );
  }

  addComment(comment: CommentC){
    this.commSer.postComment(this.message.id, comment).subscribe(
      () => {
        this.getMessage();
        this.resetParentComment();
      }
    );
  }

  // useless function --> this is done by addComment()
  addChildComment(childComment: CommentC){
    this.commSer.postComment(this.message.id, childComment).subscribe(
      () => {
        this.getMessage();
      }
    );
  }

  deleteComment(id: number) {
    this.commSer.deleteComment(id).subscribe(
      () => {
        this.getMessage();
      }
    );

  }

  deleteMessage(id: number) {
    this.msgService.deleteMessageById(id).subscribe(
      () => {
        this.getMessage();
        this.isDeleted = true;
      }
    );
  }

  resetParentComment(){
    this.parentComment = {
      text: '',
      message: this.message
    }
  }

  isCreator(){
    if (this.message.user === this.auth.getCurrentUser().username) {
      return true;
    } else {
      return false;
    }
  }

  toEdit() {
    this.editing = true;
  }

  editMsg(){
    this.msgService.editMessage(this.id, this.message).subscribe(
      (res: MessageC) => {
        this.message = res;
        this.editing = false;
      }
    );
  }

  getCategories(){
    this.categoryService.getCategories().subscribe(
      (res: Category[]) => {
        this.categories = res;
      }
    );
  }

}
