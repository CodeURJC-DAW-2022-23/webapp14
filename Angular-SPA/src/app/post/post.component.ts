import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../services/Post/post.service';
import { UsersService } from '../services/Users/users.service';
import { Post } from '../Model/post.model';
import { FormsService } from '../services/Forms/forms.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  form: any;
  posts:any[] = [];
  formsList:any[] = [];
  comment: string = "";


  imagesObject:{[key: string]: string} = {
  '/static/img/Forms-Icons/code.svg': 'assets/img/Forms-Icons/code.svg',
  '/static/img/Forms-Icons/ballings.svg': 'assets/img/Forms-Icons/ballings.svg',
  '/static/img/Forms-Icons/statistics.svg': 'assets/img/Forms-Icons/statistics.svg',
  '/static/img/Forms-Icons/thunder_icon.svg': 'assets/img/Forms-Icons/thunder_icon.svg'
};
  
  constructor(
    private activatedRoute: ActivatedRoute,  
    private http: HttpClient, 
    private forms: FormsService,
    private postService: PostService, 
    private userService: UsersService, 
    private formService: FormsService,
    private router: Router)
    {}

  userLogged = false;
  user = this.userService.currentUser();
  userName = '';
  
  ngOnInit() {

    this.userLogged = this.userService.isLogged();
    
     // Verifica si el usuario está logueado cada X segundos
    setInterval(() => {
      this.userLogged = this.userService.isLogged();
    }, 100); 

    this.form = history.state.form;
    console.log(this.form);
    this.posts = this.form.posts;
    console.log(this.form.posts); 
    this.forms.getForms().subscribe((data: any) => {
      this.formsList = data;
    })


  }

  getObjectKeys(obj: any) {
    return Object.keys(obj);
  }

  upvoteForm(id: number){
    if (this.userLogged){
    this.form.threadUpvotes += 1;
    this.postService.upvoteForm(id, this.form);
    console.log(id);
    }
  }

   upvotePost(id: number){
    if (this.userLogged){
    this.form.posts.forEach((post: Post, i: number) => {
      console.log(i)
      if(post.id == id){
       this.form.posts[i].postUpvotes += 1;
        this.postService.upvotePost(this.form.id, id, post);
      }
    })
    }
  }

  formComent(formId: number, coment: string){
    if (this.user?.username){
        this.userName = this.user.username; 
    }
  
    let post: Post = { 
    id: null,
    postContent: coment,
    postDate: new Date().toLocaleString('es-ES'), // "dd/mm/yyyy" ,
    postAuthor: this.userName,
    postUpvotes: 0
  };

   // this.posts.push(post);
    this.comment = '';
    this.posts.push(post);

    this.postService.makeComment(formId, post);
  
  }






}
