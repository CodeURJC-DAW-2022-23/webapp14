import {Component} from '@angular/core'
import {UsersService} from '../services/Users/users.service'
import {Router} from '@angular/router'

@Component({
   selector: 'app-header-logged',
   templateUrl: './header-logged.component.html',
   styleUrls: ['./header-logged.component.css'],
})
export class HeaderLoggedComponent {
   constructor(private users: UsersService, private router: Router) {}

   logOut() {
      this.users.logOut()
      this.router.navigate(['/'])
   }
}
