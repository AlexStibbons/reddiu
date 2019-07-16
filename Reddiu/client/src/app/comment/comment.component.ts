import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommentC, Vote } from '../common.models';
import { AuthenticationService } from '../security/authentication.service';
import { VoteService } from '../services/vote.service';

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

  @Output()
  emitVote: EventEmitter<void> = new EventEmitter();

  // useless helper - when message reloads, so does the boolean
  // comment would have to be marked deleted in backend
  // then, when returning comment dto, it should also return isDeleted as an attribute in common.model
  // isDeleted would then become this.comment.isDeleted
  isDeleted: boolean;

  // voting
  commVote: Vote;

  constructor(private auth: AuthenticationService,
              private voteService: VoteService) { }

  ngOnInit() {
    this.getCommVote();
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

  // voting
  getCommVote() {
    //let id = this.thisComment.id;
    //console.log(id);
    this.voteService.getCommVote(this.thisComment.id).subscribe(
      (res: Vote) => {
        this.commVote = res;
      }
    );
  }

  upvote(voted: Vote){
    this.voteService.upvote(voted).subscribe(
      (res: Vote) => {
        this.commVote = res;
      }
    );
  }

  downvote(voted: Vote) {
    this.voteService.downvote(voted).subscribe(
      (res: Vote) => {
        this.commVote = res;
      }
    );
  }

  upvoteComm(voted: Vote) {
    if (voted.upvote === false) {
      voted.upvote = true;
      voted.downvote = false;
      this.upvote(voted);
      this.emit();
    } else if (voted.upvote === true && voted.downvote === false) {
      voted.upvote = false;
      voted.downvote = false;
      this.downvote(voted);
      this.emit();
    } else {
      if (voted.upvote === true) {
        voted.upvote = false;
        voted.downvote = true;
        this.downvote(voted);
        this.emit();
      }
    }
  }

  downvoteComm(voted: Vote) {
    if (!voted.downvote) {
      voted.downvote = true;
      voted.upvote = false;
      this.downvote(voted);
      this.emit();
    } else if (voted.downvote && !voted.upvote) {
      voted.upvote = false;
      voted.downvote = false;
      this.upvote(voted);
      this.emit();
    } else if (voted.downvote) {
      voted.downvote = false;
      voted.upvote = true;
      this.upvote(voted);
      this.emit();
    }

  }

  emit() {
    this.emitVote.emit();
  }

}
