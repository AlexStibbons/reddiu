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

  interface ProjectInt {
    id?: number;
    name: string;
    description: string;
    readme: string;
    issues?: Issue[];
  }

  interface IssueInt {
    id?: number;
    username: string;
    text: string;
    votes?: number;
    projectId?: number;
  }

  export class Issue implements IssueInt {
    public id?: number;
    public username: string;
    public text: string;
    public votes?: number;
    public projectId?: number;

    constructor(spec : IssueInt) {
      this.id = spec.id;
      this.username = spec.username;
      this.text = spec.text;
      this.votes = spec.votes;
      this.projectId = spec.projectId;
    }

  }

  export class Project implements ProjectInt {

    public id?: number;
    public name: string;
    public description: string;
    public readme: string;
    public issues?: Issue[];

    constructor(spec: ProjectInt) {
      this.id = spec.id;
      this.name = spec.name;
      this.description = spec.description;
      this.readme = spec.readme;
      this.issues = spec.issues;
    }
  }

  interface VotesInt {
    upvoted?: boolean;
    downvoted?: boolean;
    issueId?: number;
  }

  export class Votes implements VotesInt {
    public upvoted?: boolean;
    public downvoted?: boolean;
    public issueId?: number;

    constructor(spec: VotesInt) {
      this.downvoted = spec.downvoted;
      this.upvoted = spec.upvoted;
      this.issueId = spec.issueId;
    }
  }