import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentC } from '../common.models';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getComments(): Observable<CommentC[]> {
    return this.http.get<CommentC[]>('/api/comments');
  }

  getCommentById(id: number): Observable<CommentC>{
    return this.http.get<CommentC>(`/api/comment/${id}`);
  }

  editComment(id: number, edited: CommentC): Observable<CommentC> {

    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});

    return this.http.put<CommentC>(`/api/comment/${id}`, JSON.stringify(edited), {headers} );
  }

  postComment(id: number, comment: CommentC): Observable<CommentC> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});
    return this.http.post<CommentC>(`api/comment/message/${id}`, JSON.stringify(comment), {headers});
  }

  deleteComment(id: number): Observable<CommentC> {
    return this.http.delete<CommentC>(`/api/comment/${id}`);
  }


}
