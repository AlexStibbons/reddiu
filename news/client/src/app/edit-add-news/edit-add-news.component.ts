import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { News, Category } from '../common.models';

@Component({
  selector: 'app-edit-add-news',
  templateUrl: './edit-add-news.component.html',
  styleUrls: ['./edit-add-news.component.css']
})
export class EditAddNewsComponent implements OnInit {

  @Input()
  forComponent: News;

  @Input()
  categories: Category[];

  @Output()
  editCreateCompo: EventEmitter<News> = new EventEmitter();

  edited: News;

  constructor() {
    this.clear();
   }

  ngOnInit() {
  }

  editCreateCompA() {
    this.editCreateCompo.emit(this.forComponent);
    this.forComponent = this.edited;
    this.clear();
   }

   editCreateComp() {
    this.editCreateCompo.emit(this.edited);
    this.clear();
  }

  clear() {
    this.edited = {
      id: -1,
      title: '',
      description: '',
      text: ''
    }
  }


}
