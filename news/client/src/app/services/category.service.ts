import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PageC, Category } from '../common.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('/api/category');
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`/api/category/${id}`);
  }


}
