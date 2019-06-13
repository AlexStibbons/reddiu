import { Component, OnInit } from '@angular/core';
import { PageC, MessageC } from '../common.models';
import { MessageService } from '../services/message.service';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  page: PageC;
  messages: MessageC[];

  constructor(private msgService: MessageService) {

   }

  ngOnInit() {
    this.firstPage();
  }

  firstPage(){
    this.msgService.getPage(0).subscribe(
      (res: PageC) => {
        this.page = res;
        this.messages = res.content;
      }
    );
  }

  lastPage(){
    this.msgService.getPage(this.page.totalPages - 1).subscribe(
      (res: PageC) => {
        this.page = res;
        this.messages = res.content;
      }
    );
  }

  nextPage(){
    if (this.page.hasNext) {
      this.msgService.getPage(this.page.index + 1).subscribe(
        (res: PageC) => {
          this.page = res;
          this.messages = res.content;
        }
      );
    }
  }

  previousPage(){
    if (this.page.hasPrevious) {
      this.msgService.getPage(this.page.index - 1).subscribe(
        (res: PageC) => {
          this.page = res;
          this.messages = res.content;
        }
      );
    }
  }

}
