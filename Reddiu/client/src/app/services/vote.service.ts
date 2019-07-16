import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vote } from '../common.models';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(private http: HttpClient) { }

  getMsgVote(id: number): Observable<Vote> {
    return this.http.get<Vote>(`api/vote/msg/${id}`);
  }

  getCommVote(id: number): Observable<Vote> {
    return this.http.get<Vote>(`api/vote/comm/${id}`);
  }

  upvote(voted: Vote): Observable<Vote> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<Vote>(`api/upvote`, JSON.stringify(voted), {headers});
  }

  downvote(voted: Vote) {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});
    return this.http.put<Vote>(`api/downvote`, JSON.stringify(voted), {headers});
  }

}
