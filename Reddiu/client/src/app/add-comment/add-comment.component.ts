import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MessageC, CommentC } from '../common.models';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  comment: CommentC;

  @Input()
  parentCommentId: number;

  @Output()
  addCommentEmit: EventEmitter<CommentC> = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.resetComment();
  }

  addComment(){
    if (this.parentCommentId) {
      this.comment = {
        text: this.comment.text,
        parentCommentId: this.parentCommentId
      };
      this.addCommentEmit.emit(this.comment);
      this.resetComment();
    } else {
      this.addCommentEmit.emit(this.comment);
      this.resetComment();
    }

  }

  resetComment(){
    this.comment = {
      text: ''
    }
  }

}
