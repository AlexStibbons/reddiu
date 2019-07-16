/* Contains interfaces and classes for:
  Page, Category, Message, Comment, New user, and Vote
*/

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
  score?: number;
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
  score?: number;
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
  public score?: number;

  constructor(spec: MessageInt) {
    this.id = spec.id;
    this.title = spec.title;
    this.text = spec.text;
    this.category = spec.category;
    this.user = spec.user;
    this.comments = spec.comments;
    this.score = spec.score;
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
  public score?: number;

  constructor(spec: CommentInt) {
    this.id = spec.id;
    this.text = spec.text;
    this.message = spec.message;
    this.user = spec.user;
    this.parentComment = spec.parentComment;
    this.parentCommentId = spec.parentCommentId;
    this.childComments = spec.childComments;
    this.score = spec.score;
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

interface VoteInt {
  id: number;
  upvote: boolean;
  downvote: boolean;
  username: string;
  messageId?: number;
  commentId?: number;
}

export class Vote implements VoteInt {
  public id: number;
  public upvote: boolean;
  public downvote: boolean;
  public username: string;
  public messageId?: number;
  public commentId?: number;

  constructor(spec: VoteInt) {
    this.id = spec.id;
    this.upvote = spec.upvote;
    this.downvote = spec.downvote;
    this.username = spec.username;
    this.messageId = spec.messageId;
    this.commentId = spec.commentId;
  }
}
