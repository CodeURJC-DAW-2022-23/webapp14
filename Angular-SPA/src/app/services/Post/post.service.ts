import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
    
      constructor(private http: HttpClient) { }
    
     upvoteForm(id: number, form: any) {
        return this.http.put(`https://localhost:8443/api/forms/${id}`, form).subscribe();
    }

      upvotePost(idForm: number, idPost:number, post: any ) {
        return this.http.put(`https://localhost:8443/api/posts/${idForm}/comment/${idPost}`, post).subscribe();
    }

    makeComment(formId: number, comment: string) {
    const url = `https://localhost:8443/api/posts/${formId}`; // URL completa de la solicitud POST
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = JSON.stringify({ comment: comment });

    return this.http.post(url, body, { headers: headers });
  }


 }