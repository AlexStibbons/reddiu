import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { News, CommentC } from '../common.models';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  // getting news
  private sub: any;
  news: News;
  id: number;

  // adding comment
  comment: CommentC;

  constructor(private route: ActivatedRoute,
              private newsService: NewsService) { }

  ngOnInit() {
    this.getNews();
  }

  getNews(){
    this.sub = this.route.params.subscribe(
      params => {
        this.id = params['id'];
        this.newsService.getNewsById(this.id).subscribe(
          (res: News) => {
            this.news = res;
          }
        );
      }
    );
  }

  addComment(comment: CommentC) {
    this.newsService.addComment(this.id, comment).subscribe(
      ()=> {
        this.getNews();
        this.resetComment();
      }
    );
  }

  resetComment(){
    this.comment = {
      username: '',
      text: ''
    }
  }

}
