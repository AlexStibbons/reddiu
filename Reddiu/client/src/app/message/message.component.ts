import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message.service';
import { ActivatedRoute } from '@angular/router';
import { MessageC, CommentC } from '../common.models';
import { CommentService } from '../services/comment.service';

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

  constructor(private msgService: MessageService,
              private route: ActivatedRoute,
              private commSer: CommentService) { }

  ngOnInit() {
    this.getMessage();
    this.resetParentComment();
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

  addChildComment(childComment: CommentC){ // child received from comment component
    this.commSer.postComment(this.message.id, childComment).subscribe(
      () => {
        this.getMessage();
      }
    );
  }

  resetParentComment(){
    this.parentComment = {
      text: '',
      message: this.message
    }
  }

}
