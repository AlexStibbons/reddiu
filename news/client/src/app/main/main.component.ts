import { Component, OnInit } from '@angular/core';
import { PageC, News, Category } from '../common.models';
import { NewsComponent } from '../news/news.component';
import { NewsService } from '../services/news.service';
import { CategoryService } from '../services/category.service';
import { AuthenticationService } from '../security/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  // basics
  public page: PageC;
  public news: News[];

  // search/filtering news
  public searchTitle: string; // if chosen, the title query string is placed here
  public categorySearch: Category; // if chosen, category for search is placed here
  public categoryId: any; // a trick, but works
  public categories: Category[]; // to get all categories in dropdown menu

  // to edit news
  public forComp: News;

  // to edit in main
  public edited: News;

  constructor(private newsService: NewsService,
              private categoryService: CategoryService,
              private auth: AuthenticationService,
              private router: Router) {

    this.searchTitle = '';
    this.categoryId = '';
    this.categorySearch = {
      id: '',
      name: ''
    };
    this.clear();
  }

  ngOnInit() {
    this.getFirstPage();
    this.getCategories();
  }

  getFirstPage() {
    this.newsService.getNews(0, this.searchTitle, this.categoryId).subscribe( // works, because backend
      (res: PageC) => {
        this.page = res;
        this.news = res.content;
      }
    );
  }

  nextPage() {
    // since there is already a categorySearch object with its own id
    // insted of memorizing categoryId, the object could (and should be used)
    // so instead of this.categoryId, it should be this.categorySearch.id
    this.newsService.getNews(this.page.index + 1, this.searchTitle, this.categoryId).subscribe(
      (res: PageC) => {
        this.page = res;
        this.news = res.content;
      }
    );
  }

  previousPage(){
    this.newsService.getNews(this.page.index - 1, this.searchTitle, this.categoryId).subscribe(
      (res: PageC) => {
        this.page = res;
        this.news = res.content;
      }
    );
  }

  filterNews() {
    this.categoryId = this.categorySearch.id;
    this.newsService.getNews(0, this.searchTitle, this.categoryId).subscribe(
      (res: PageC) => {
        this.page = res;
        this.news = res.content;
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

  clearSearch() {
    this.searchTitle = '';
    this.categoryId = '';
    this.categorySearch = {
      id: '',
      name: ''
    };
    this.getFirstPage();
  }

  loggedIn() {
    return this.auth.isLoggedIn();
  }

  details(id: number) {
    this.router.navigate([`/news/${id}`]);
  }

  delete(id: number) {
    this.newsService.deleteNews(id).subscribe(
      () => {
        this.getFirstPage();
      }
    );
  }

  edit(item: News) {
    this.edited = item;
    this.forComp = item;
  }

  clear(){
    this.edited = {
      id: -1,
      title: '',
      description: '',
      text: ''
    }
  }

  editCreate() {
    if (this.edited.id > -1) {
      this.newsService.edit(this.edited).subscribe(
        () => {
          this.getFirstPage();
          this.clear();
        }
      );
    } else {
      this.newsService.create(this.edited).subscribe(
        (res: News) => {
          this.getFirstPage();
          this.clear();
        }
      );
    }
  }

  editCreateComp(news: News) {
    if (news.id > -1) {
      this.newsService.edit(news).subscribe(
        () => {
          this.getFirstPage();
          this.clear();
        }
      );
    } else {
      this.newsService.create(news).subscribe(
        (res: News) => {
          this.getFirstPage();
          this.clear();
        }
      );
    }
  }

}
