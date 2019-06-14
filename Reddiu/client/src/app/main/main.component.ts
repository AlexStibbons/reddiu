import { Component, OnInit } from '@angular/core';
import { PageC, MessageC } from '../common.models';
import { MessageService } from '../services/message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  // basic necessities
  public page: PageC;
  public messages: MessageC[];

  // search title necessities --> how to avoid this, esp because
  // there should be show by category too
  public searchTitle: string;
  public isSearchTitle: boolean;

  constructor(private msgService: MessageService,
              private router: Router) {

   }

  ngOnInit() {
    this.firstPage();
  }

  firstPage(){
    if (this.isSearchTitle) {
      this.msgService.getPage(0, 'title', this.searchTitle).subscribe(
        (res: PageC) => {
          this.page = res;
          this.messages = res.content;
          this.isSearchTitle = true;
        }
      );
    } else {
      this.msgService.getPage(0).subscribe(
        (res: PageC) => {
          this.page = res;
          this.messages = res.content;
          this.isSearchTitle = false;
        }
      );
    }
  }

  lastPage() {
    if (this.isSearchTitle) {
      this.msgService.getPage(this.page.totalPages - 1, 'title', this.searchTitle).subscribe(
        (res: PageC) => {
          this.page = res;
          this.messages = res.content;
          this.isSearchTitle = true;
        }
      );
    } else {
      this.msgService.getPage(this.page.totalPages - 1).subscribe(
        (res: PageC) => {
          this.page = res;
          this.messages = res.content;
        }
      );
    }
  }

  nextPage(){
    if (this.page.hasNext) {
      if (this.isSearchTitle) {
        this.msgService.getPage(this.page.index + 1, 'title', this.searchTitle).subscribe(
          (res: PageC) => {
            this.page = res;
            this.messages = res.content;
            this.isSearchTitle = true;
          }
        );

      } else {
        this.msgService.getPage(this.page.index + 1).subscribe(
          (res: PageC) => {
            this.page = res;
            this.messages = res.content;
            this.isSearchTitle = false;
          }
        );
      }
    }
  }

  previousPage(){
    if (this.page.hasPrevious) {
      if (this.isSearchTitle) {
        this.msgService.getPage(this.page.index - 1, 'title', this.searchTitle).subscribe(
          (res: PageC) => {
            this.page = res;
            this.messages = res.content;
            this.isSearchTitle = true;
          }
        );
      } else {
        this.msgService.getPage(this.page.index - 1).subscribe(
          (res: PageC) => {
            this.page = res;
            this.messages = res.content;
            this.isSearchTitle = false;
          }
        );
      }
    }
  }

  searchByTitle() { // has next and previous only because of isSearchTitle and saving searchTitle
    this.msgService.getPage(0, 'title', this.searchTitle).subscribe(
      (res: PageC) => {
        this.page = res;
        this.messages = res.content;
        this.isSearchTitle = true;
        //this.searchTitle = '';
      }
    );
  }

  readMore(id: number){
    this.router.navigate([`/message/${id}`]);
  }

}
