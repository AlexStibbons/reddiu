
interface PageInt {
  size: number;
  index: number;
  content: any[];
  hasNext: boolean;
  hasPrevious: boolean;
  numberOfItems: number;
  totalPages: number;
}

export class PageC implements PageInt {
  public size: number;
  public index: number;
  public content: any[];
  public hasNext: boolean;
  public hasPrevious: boolean;
  public numberOfItems: number;
  public totalPages: number;

  constructor(spec: PageInt) {
    this.size = spec.size;
    this.index = spec.index;
    this.content = spec.content;
    this.hasNext = spec.hasNext;
    this.hasPrevious = spec.hasPrevious;
    this.numberOfItems = spec.numberOfItems;
    this.totalPages = spec.totalPages;
  }
}


interface NewsIn {
  id?: number;
  title: string;
  description: string;
  text: string;
  category?: Category;
  comments?: CommentC[];
}

export class News implements NewsIn {
  public id?: number;
  public title: string;
  public description: string;
  public text: string;
  public category?: Category;
  public comments?: CommentC[];

  constructor(spec: NewsIn) {
    this.id = spec.id;
    this.title = spec.title;
    this.description = spec.description;
    this.text = spec.text;
    this.category = spec.category;
    this.comments = spec.comments;
  }
}

interface CommentIn {
  id?: number;
  username:string;
  text: string;
  newsId?: number;
  parentCommentId?: number;
  isParentComment?: boolean;
  childComments?: CommentC[];
}

export class CommentC implements CommentIn {

  public id?: number;
  public username:string;
  public text: string;
  public newsId?: number;
  public parentCommentId?: number;
  public isParentComment?: boolean;
  public childComments?: CommentC[];

  constructor(spec: CommentIn) {
    this.id = spec.id;
    this.username = spec.username;
    this.text = spec.text;
    this.newsId = spec.newsId;
    this.parentCommentId = spec.parentCommentId;
    this.isParentComment = spec.isParentComment;
    this.childComments = spec.childComments;
  }
}

interface CategoryIn {
  id?: any;
  name: string;
}

export class Category implements CategoryIn {
  public id?: any;
  public name: string;

  constructor(spec: CategoryIn) {
    this.id = spec.id;
    this.name = spec.name;
  }
}
