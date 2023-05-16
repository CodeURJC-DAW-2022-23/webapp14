import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Observable} from 'rxjs'
import {UsersService} from '../Users/users.service'
import {User} from 'src/app/Model/users.model'

@Injectable({
   providedIn: 'root',
})
export class ClientService {
   constructor(private http: HttpClient, private userService: UsersService) {}

   getPacks(): Observable<any[]> {
      return this.http.get<any[]>('/api/packs/')
   }

   getTags(): Observable<any[]> {
      return this.http.get<any[]>('/api/tags/')
   }

   getUser(): User | undefined {
      return this.userService.user
   }
}
