import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Thread } from '../Model/thread.model';
import { FormsService } from '../services/Forms/forms.service';
import { UsersService } from '../services/Users/users.service';


@Component({
  selector: 'app-forms-maker',
  templateUrl: './forms-maker.component.html',
  styleUrls: ['./forms-maker.component.css']
})
export class FormsMakerComponent {
 
  constructor(private router:Router, private forms: FormsService, private userService: UsersService) { }
  
   id: number = 0;
   formsList:any[] = [];
   threadTitle: string = "";
   content_short: string = "";
   threadContent: string = "";
   user = this.userService.currentUser();
   userName = '';
   imageSelected: string = "";

   images: string[] = [
    'code.svg',
    'ballings.svg',
    'statistics.svg',
    'thunder_icon.svg'
   ]

    myThread: Thread = {
    id: this.id,
    threadTitle: this.threadTitle,
    threadContent_short: this.content_short,
    threadContent: this.threadContent,
    threadDate: new Date().toLocaleString('es-ES'), 
    threadAuthor: this.userName,
    threadUpvotes: 0,
    threadImage: '' ,
    posts: []
  }

  ngOnInit() {
    this.forms.getForms().subscribe((data: any) => {
      this.formsList = data;
      this.id = this.formsList.length + 1;
    })

    if (this.user?.username){
        this.userName = this.user.username; 
    }
  }
  
 
  createForm(){
    console.log(this.imageSelected);
    this.myThread.threadTitle = this.threadTitle;
    this.myThread.threadContent_short = this.content_short; 
    this.myThread.threadContent = this.threadContent;
    this.myThread.threadAuthor = this.userName;
    this.myThread.threadImage = `/static/img/Forms-Icons/${this.imageSelected}`;
    console.log(this.myThread)
    this.forms.createForm(this.myThread).subscribe((data: any) => {
      this.router.navigate(['/forms']);
    })
  }
  


  getObjectKeys(obj: any) {
    return Object.keys(obj);
  }
}
