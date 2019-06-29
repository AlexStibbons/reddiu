import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Issue } from '../common.models';
import { IssueService } from '../services/issue.service';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  @Input()
  issue: Issue;

  @Output()
  upvoted: EventEmitter<number> = new EventEmitter();

  @Output()
  downvoted: EventEmitter<number> = new EventEmitter();

  // helpers
  upvotedBoolean: boolean;
  downvotedBoolean: boolean;
  // booleans should be emitted to project component, then re-inserted into the same issue
  // it does not work because they are both marked false each time the page loads (constructor)
  // these booleans should (likely) be in backend (issue model and issue dto) for this component to work properly
 
  constructor(private issueService: IssueService,
              private auth: AuthenticationService) { 
                this.upvotedBoolean = false;
                this.downvotedBoolean = false;
              }

  ngOnInit() {

  }

  upvote() {
    if (this.upvotedBoolean === false) {

      this.upvotedBoolean = true;
      this.downvotedBoolean = false;
      this.upvoted.emit(this.issue.id);

      console.log(this.upvotedBoolean);
      console.log(this.downvotedBoolean);

    } else if (this.upvotedBoolean === true) {
      this.upvotedBoolean = false;
      this.downvotedBoolean = true;
      this.downvoted.emit(this.issue.id);
      console.log(this.upvotedBoolean);
      console.log(this.downvotedBoolean);

    } else {
      if (this.upvotedBoolean === true && this.downvotedBoolean === false) {
        this.upvotedBoolean = false;
        this.downvotedBoolean = false;
        this.downvoted.emit(this.issue.id);
        console.log(this.upvotedBoolean);
        console.log(this.downvotedBoolean);

      }
    }
  }

  downvote() {
    if (!this.downvotedBoolean) {
      this.downvotedBoolean = true;
      this.upvotedBoolean = false;
      this.downvoted.emit(this.issue.id);
    } else if (this.downvotedBoolean) {
      this.downvotedBoolean = false;
      this.upvotedBoolean = true;
      this.upvoted.emit(this.issue.id);
    } else if (this.downvotedBoolean && this.upvotedBoolean == false) {
      this.upvotedBoolean = false;
      this.downvotedBoolean = false;
      this.upvoted.emit(this.issue.id);
    }
  }

  loggedIn(){
    return this.auth.isLoggedIn();
  }


}
