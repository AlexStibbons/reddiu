import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message.service';
import { ActivatedRoute } from '@angular/router';
import { MessageC, CommentC, Category, Vote } from '../common.models';
import { CommentService } from '../services/comment.service';
import { AuthenticationService } from '../security/authentication.service';
import { CategoryService } from '../services/category.service';
import { VoteService } from '../services/vote.service';


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

  // voting
  msgVote: Vote;

  constructor(private msgService: MessageService,
              private route: ActivatedRoute,
              private commSer: CommentService,
              private auth: AuthenticationService,
              private categoryService: CategoryService,
              private voteService: VoteService) { }

  ngOnInit() {
    this.getMessage();
    this.resetParentComment();
    this.getCategories();
    this.getMsgVote();
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
      message: this.message,
      score: 1
    };
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

// VOTING

  getMsgVote(){
    this.voteService.getMsgVote(this.id).subscribe(
      (res: Vote) => {
        this.msgVote = res;
        //this.getMessage();
        console.log(this.msgVote);
      }
    );
  }

// up/downvote in services
  upvotingMsg(voted: Vote) {
    this.voteService.upvote(voted).subscribe(
      (res: Vote) => {
        this.msgVote = res;
        this.getMessage();
      }
    );
  }

  downvotingMsg(voted: Vote) {
    this.voteService.downvote(voted).subscribe(
      (res: Vote) => {
        this.msgVote = res;
        this.getMessage();
      }
    );
  }

  // up/downvote for message

  // can there be just a vote function - does it matter whether the
  // user pressed upvote or downvote?
  // also
  // can the if cases be somehow centralized so DRY's followed?
  upvoteMsg(voted: Vote) {
    if (voted.upvote === false) {
      voted.upvote = true;
      voted.downvote = false;
      this.upvotingMsg(voted);
    } else if (voted.upvote === true && voted.downvote === false) {
      voted.upvote = false;
      voted.downvote = false;
      this.downvotingMsg(voted);
    } else {
      if (voted.upvote === true) {
        voted.upvote = false;
        voted.downvote = true;
        this.downvotingMsg(voted);
      }
    }
  }

  downvoteMsg(voted: Vote) {
    if (!voted.downvote) {
      voted.downvote = true;
      voted.upvote = false;
      this.downvotingMsg(voted);
    } else if (voted.downvote && !voted.upvote) {
      voted.upvote = false;
      voted.downvote = false;
      this.upvotingMsg(voted);
    } else if (voted.downvote) {
      voted.downvote = false;
      voted.upvote = true;
      this.upvotingMsg(voted);
    }
  }

  // up/downvote comments [once a comment is voted upon, the message should reload in
  // order to show the updated comment scores]
  voteComm(){
    this.getMessage();
  }
}
