import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../services/Post/post.service';
import { UsersService } from '../services/Users/users.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  form: any;
  posts:any[] = [];
  comment: string = "";


  imagesObject:{[key: string]: string} = {
  '/static/img/Forms-Icons/code.svg': '/assets/img/Forms-Icons/code.svg',
  '/static/img/Forms-Icons/ballings.svg': '/assets/img/Forms-Icons/ballings.svg',
  '/static/img/Forms-Icons/statistics.svg': '/assets/img/Forms-Icons/statistics.svg',
  '/static/img/Forms-Icons/thunder_icon.svg': '/assets/img/Forms-Icons/thunder_icon.svg'
};
  
  constructor(private activatedRoute: ActivatedRoute,  private http: HttpClient, private postService: PostService, private userService: UsersService){}
  userLogged = false;
  
  ngOnInit() {

    this.userLogged = this.userService.isLogged();
     // Verifica si el usuario está logueado cada X segundos
    setInterval(() => {
      this.userLogged = this.userService.isLogged();
    }, 100); 

    this.form = history.state.form;
    this.posts = this.form.posts;
    console.log(this.form); 
  }

  getObjectKeys(obj: any) {
    return Object.keys(obj);
  }

  upvoteForm(id: number){
    this.form.threadUpvotes += 1;
    this.postService.upvoteForm(id, this.form);
    console.log(id);
  }

   upvotePost(id: number){
    console.log("id del post: " + id);
    this.posts.forEach((post) => {
      if(post.id == id){
        post.upvotes += 1;
        this.postService.upvotePost(this.form.id, id, post);
      }
    })
    
  }

  formComent(formId: number, coment: string){
   
    this.postService.makeComment(formId, coment);

  }





}
