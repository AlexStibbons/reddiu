import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { PageC, MessageC } from '../common.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  getPage(page: number, param?: string, query?:string): Observable<PageC> {

   /* let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', '2');
    return this.http.get<PageC>('api/messages', {params})*/

    if (param && query) {
      return this.http.get<PageC>(`/api/messages/search?${param}=${query}&page=${page}&size=2`);
    }

    return this.http.get<PageC>(`/api/messages?page=${page}&size=2`);
  }

  getMessageById(id: number): Observable<MessageC> {
    return this.http.get<MessageC>(`/api/messages/${id}`);
  }

  getMessageByTitle(title: string): Observable<PageC>{
    return this.http.get<PageC>(`/api/messages/search?title=${title}&page=0&size=2`);
  }

  deleteMessageById(id: number): Observable<MessageC> {
    return this.http.delete<MessageC>(`/api/messages/${id}`);
  }

  editMessage(id: number, message: MessageC): Observable<MessageC> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.put<MessageC>(`/api/messages/${id}`, JSON.stringify(message), {headers});
  }

  createMessage(message: MessageC): Observable<MessageC> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<MessageC>('/api/messages/new', JSON.stringify(message), {headers});
  }

}
