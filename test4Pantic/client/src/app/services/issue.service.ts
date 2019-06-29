import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Issue } from '../common.models';

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private http: HttpClient) { }

  voteUp(issueId: number): Observable<Issue>{
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<Issue>(`api/issue/up/${issueId}`, {headers});
  }

  voteDown(issueId: number): Observable<Issue> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<Issue>(`api/issue/down/${issueId}`, {headers});
  }
}
