import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PageC, Issue, Project } from '../common.models';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getProjects(page: number, name?: string): Observable<PageC> {
    return this.http.get<PageC>(`/api/projects/search?name=${name}&page=${page}&size=3`);
  }

  getProjectsSimple(page: number): Observable<PageC> {
    return this.http.get<PageC>(`/api/projects/search?page=${page}&size=3`);
  }

  oneProject(id: number): Observable<Project> {
    return this.http.get<Project>(`api/projects/${id}`);
  }

  addIssue(projectId: number, issue: Issue): Observable<Issue> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<Issue>(`/api/issue/projects/${projectId}`, JSON.stringify(issue), {headers});
  }

  addProject(project: Project): Observable<Project> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<Project>('/api/projects/new', JSON.stringify(project), {headers});
  }

  editProject(projectId: number, project: Project): Observable<Project> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<Project>(`/api/projects/${projectId}`, JSON.stringify(project), {headers});
  }

  deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`/api/projects/${projectId}`);
  }
}
