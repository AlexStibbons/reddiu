import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { UserProfile, MessageC, CommentC } from '../common.models';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  username: string;
  private sub: any;

  userProfile: UserProfile;
  messages: MessageC[];
  comments: CommentC[];

  showComments: boolean;
  showMessages: boolean;

  constructor(private userService: UserService,
              private route: ActivatedRoute) {

                this.showMessages = true;
                this.showComments = false;
  }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.sub = this.route.params.subscribe(params => {

      this.username = params.username;

      this.userService.getUserProfile(this.username).subscribe(
        (res: UserProfile) => {
          this.userProfile = res;
          this.messages = res.messages;
          this.comments = res.comments;
        }
      );
    });
  }

}
