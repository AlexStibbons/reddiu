import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommentC } from '../common.models';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input()
  thisComment: CommentC;

  @Output()
  addComment: EventEmitter<CommentC> = new EventEmitter();

  @Output()
  deleteCommentEmit: EventEmitter<number> = new EventEmitter();

  // useless helper - when message reloads, so does the boolean
  // comment would have to be marked deleted in backend
  // then, when returning comment dto, it should also return isDeleted as an attribute in common.model
  // isDeleted would then become this.comment.isDeleted
  isDeleted: boolean;

  constructor(private auth: AuthenticationService) { }

  ngOnInit() {
  }

  addCommentToMain(comment: CommentC){
    this.addComment.emit(comment);
  }

  deleteComment(id: number) {
    this.deleteCommentEmit.emit(id);
    this.isDeleted = true;
  }

  isCreator(){
    if ( this.thisComment.user === this.auth.getCurrentUser().username ){
      return true;
    } else {
      return false;
    }
  }

}
