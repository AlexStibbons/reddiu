import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Issue } from '../common.models';

@Component({
  selector: 'app-add-issue',
  templateUrl: './add-issue.component.html',
  styleUrls: ['./add-issue.component.css']
})
export class AddIssueComponent implements OnInit {

  newIssue: Issue;

  @Output()
  addIssueEmit: EventEmitter<Issue> = new EventEmitter();

  constructor() {

    this.newIssue = {
      username: '',
      text: ''
    }

   }

  ngOnInit() {
  }

  addIssue(){
    this.addIssueEmit.emit(this.newIssue);
    this.newIssue = {
      username: '',
      text: ''
    }
  }

}
