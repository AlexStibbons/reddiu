interface PageInt {
  size: number;
  index: number;
  content: any[];
  hasNext: boolean;
  hasPrevious: boolean;
  numberOfMessages: number;
  totalPages: number;
}

export class PageC implements PageInt {
  public size: number;
  public index: number;
  public content: any[];
  public hasNext: boolean;
  public hasPrevious: boolean;
  public numberOfMessages: number;
  public totalPages: number;

  constructor(spec: PageInt) {
    this.size = spec.size;
    this.index = spec.index;
    this.content = spec.content;
    this.hasNext = spec.hasNext;
    this.hasPrevious = spec.hasPrevious;
    this.numberOfMessages = spec.numberOfMessages;
    this.totalPages = spec.totalPages;
  }
}

interface MessageInt {
  id?: number;
  title: string;
  text: string;
  category?: Category;
  user?: string;
  comments?: CommentC[];
}

interface CategoryInt {
  id?: number;
  name: string;
}

interface CommentInt {
  id?: number;
  text: string;
  message?: MessageC;
  user?: string;
  parentComment?: boolean;
  parentCommentId?: number;
  childComments?: CommentC[];
}

export class Category implements CategoryInt {

  public id?: number;
  public name: string;

  constructor(spec: CategoryInt) {
    this.id = spec.id;
    this.name = spec.name;
  }
}

export class MessageC implements MessageInt {
  public id?: number;
  public title: string;
  public text: string;
  public category?: Category;
  public user?: string;
  public comments?: CommentC[];

  constructor(spec: MessageInt) {
    this.id = spec.id;
    this.title = spec.title;
    this.text = spec.text;
    this.category = spec.category;
    this.user = spec.user;
    this.comments = spec.comments;
  }
}

export class CommentC implements CommentInt {
  public id?: number;
  public text: string;
  public message?: MessageC;
  public user?: string;
  public parentComment?: boolean;
  public parentCommentId?: number;
  public childComments?: CommentC[];

  constructor(spec: CommentInt) {
    this.id = spec.id;
    this.text = spec.text;
    this.message = spec.message;
    this.user = spec.user;
    this.parentComment = spec.parentComment;
    this.parentCommentId = spec.parentCommentId;
    this.childComments = spec.childComments;
  }
}

interface NewUserInt {
  id?: number;
  firstName?: string;
  lastName?: string;
  username?: string;
  password?: string;
  roles?: any[];
}

export class NewUser implements NewUserInt {
  public id?: number;
  public firstName: string;
  public lastName: string;
  public username: string;
  public password: string;
  public roles: any[];

  constructor(spec: NewUserInt) {
    this.id = spec.id;
    this.firstName = spec.firstName;
    this.lastName = spec.lastName;
    this.username = spec.username;
    this.password = spec.password;
    this.roles = spec.roles;
  }
}
/* FROM CLASS
export class Page {
  private page: any = {
    size: 2,
    index: 0,
    content: [],
    hasNext: true,
    hasPrevious: false,
    numberOfMessages: 0,
    totalPages: 0
  }
}*/
