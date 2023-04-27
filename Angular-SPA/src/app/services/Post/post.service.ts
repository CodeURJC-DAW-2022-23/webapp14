import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from 'src/app/Model/post.model';

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

    makeComment(formId: number, post: any) {
      console.log(formId)
      console.log(post)
    const url = `/api/posts/comment/${formId}`; 
    return this.http.post(url, post).subscribe(
          (response) => console.log("Form actualizado"),
          (error) => alert("Error al registrar el post")
    );
  }


 }