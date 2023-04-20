import { Component } from '@angular/core';
import { UsersService } from '../services/Users/users.service';

@Component({
  selector: 'app-header-logged',
  templateUrl: './header-logged.component.html',
  styleUrls: ['./header-logged.component.css']
})
export class HeaderLoggedComponent {


  constructor(private users: UsersService) { }

  logOut(){
    this.users.logOut();
  }

}
