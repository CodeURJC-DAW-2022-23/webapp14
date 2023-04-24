import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
    
      constructor(private http: HttpClient) { }
    
     upvoteForm(id: number, form: any) {
      console.log(form);
         this.http.put(`/api/forms/${id}`, form).subscribe(
          (response) => console.log("Form actualizado"),
          (error) => alert("Error al registrar el post")
         );
    }

      upvotePost(idForm: number, idPost:number, post: any ) {
        return this.http.put(`/api/posts/${idForm}/comment/${idPost}`, post).subscribe();
    }

    makeComment(formId: number, comment: string) {
    const url = `/api/posts/${formId}`; 
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = JSON.stringify({ comment: comment });

    return this.http.post(url, body, { headers: headers });
  }


 }