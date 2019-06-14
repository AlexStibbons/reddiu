import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommentC } from '../common.models';

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

  constructor() { }

  ngOnInit() {
  }

  addCommentToMain(comment: CommentC){
    this.addComment.emit(comment);
  }

}
