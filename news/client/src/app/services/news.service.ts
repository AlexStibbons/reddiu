import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PageC, News, CommentC } from '../common.models';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  getNews(page: number, title?: string, categoryId?: any): Observable<PageC> {
    return this.http.get<PageC>(`/api/news/search?title=${title}&categoryId=${categoryId}&page=${page}&size=3`);
  }

  getNewsById(id: number): Observable<News> {
    return this.http.get<News>(`/api/news/${id}`);
  }

  addComment(id: number, comment: CommentC): Observable<CommentC> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});
    return this.http.post<CommentC>(`/api/news/${id}/comment`, JSON.stringify(comment), {headers});
  }

  deleteNews(id: number): Observable<News> {
    return this.http.delete<News>(`/api/news/${id}`);
  }

  edit(news: News): Observable<News> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});
    return this.http.put<News>(`/api/news/${news.id}`, JSON.stringify(news), {headers});
  }

  create(news: News): Observable<News> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});
    return this.http.post<News>(`/api/news/new`, JSON.stringify(news), {headers});
  }

}
