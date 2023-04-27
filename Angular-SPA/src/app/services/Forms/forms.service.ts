import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Thread } from 'src/app/Model/thread.model';

@Injectable({
  providedIn: 'root'
})
export class FormsService {
    listForms: any[] = [];
    
      constructor(private http: HttpClient) { }
    
     getForms(): Observable<any[]> {
        return this.http.get<any[]>('/api/forms/');
    }

    createForm(form: Thread) {
        return this.http.post('/api/forms/', form);
    }

    

 /*  getForm(id: number){
      this.http.get<any[]>(`/api/forms/`).pipe(
      tap((form: any) => {
        this.listForms = form; 
      })
    );
    this.listForms.forEach(form => {
      if (form.id == id) {
        return form;
      }
    })
  }
*/
   
   
  
 }