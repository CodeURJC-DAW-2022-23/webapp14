import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private querySubject = new Subject<string>();

  constructor(private http: HttpClient) { }

  setQuery(query: string) {
    this.querySubject.next(query);
  }

  getSearchPacks(): Observable<any[]> {
    return this.querySubject.asObservable().pipe(
      switchMap(query => {
        return this.http.get<any[]>('/api/search/' + query);
      })
    );
  }
}
