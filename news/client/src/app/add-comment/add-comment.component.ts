import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommentC } from '../common.models';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  @Input()
  parentId: number;

  comment: CommentC;

  @Output()
  addComment: EventEmitter<CommentC> = new EventEmitter();

  constructor() {
    this.comment = {
      username: '',
      text: ''
    }
  }

  ngOnInit() {
  }

  addCommentA() {
    if (this.parentId) {
      this.comment = {
        username: this.comment.username,
        text: this.comment.text,
        parentCommentId: this.parentId
      };
      this.addComment.emit(this.comment);
      this.resetComment();
    } else {
      this.addComment.emit(this.comment);
      this.resetComment();
    }
  }

  resetComment() {
    this.comment = {
      username: '',
      text: ''
    }
  }

}
