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

  constructor(public router: Router, private userService: UsersService) {}

  // Verifica si el usuario está logueado al inicializar el componente
  ngOnInit() {
  this.isLogged = this.userService.isLogged();
  
  // Verifica si el usuario está logueado cada X segundos
  setInterval(() => {
    this.isLogged = this.userService.isLogged();
  }, 1000); 
}
  
}
