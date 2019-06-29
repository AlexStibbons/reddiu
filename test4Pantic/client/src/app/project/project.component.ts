import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../services/project.service';
import { IssueService } from '../services/issue.service';
import { ActivatedRoute } from '@angular/router';
import { Project, Issue, Votes } from '../common.models';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  id: number;
  project: Project;
  private sub: any;


  constructor(private projectService: ProjectService,
              private issueService: IssueService,
              private auth: AuthenticationService,
              private route: ActivatedRoute) { }


  ngOnInit() {
    this.getProject();
  }

  getProject() {
    this.sub = this.route.params.subscribe(
      params => {
        this.id = params['id'];
        this.projectService.oneProject(this.id).subscribe(
          (res: Project) => {
            this.project = res;
          }
        );
      }
    );

  }

  addIssue(issue: Issue) {
    this.projectService.addIssue(this.project.id, issue).subscribe(
      (res: Issue) => {
        this.getProject();
      }
    );
  }

  upvote(id: number) {
    this.issueService.voteUp(id).subscribe(
      (res: Issue) => {
        console.log(res);
        this.getProject();
      }
    );
  }

  downvote(id: number) {
    this.issueService.voteDown(id).subscribe(
      (res: Issue) => {
        console.log(res);
        this.getProject();
      }
    );
  }

  

  loggedIn(){
    return this.auth.isLoggedIn();
  }


}
