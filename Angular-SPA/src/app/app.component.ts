import { Component, Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { UsersService } from './services/Users/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  isLogged: boolean = false;

  constructor(public router: Router, private userService: UsersService) {
    // verifica si el usuario está logueado al cargar la aplicación
    this.isLogged = this.userService.isLogged();
    
    // verifica si el usuario está logueado cada vez que cambia la URL
    this.router.events.pipe(
      filter((event: any) => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.isLogged = this.userService.isLogged();
    });
  }
  
}
