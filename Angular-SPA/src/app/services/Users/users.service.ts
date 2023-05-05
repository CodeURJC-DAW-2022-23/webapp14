import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {User} from 'src/app/Model/users.model'
import {Observable} from 'rxjs'

@Injectable({
   providedIn: 'root',
})
export class UsersService {
   logged: boolean = false
   user?: User
   constructor(private http: HttpClient) {
      this.reqIsLogged()
   }

   reqIsLogged() {
      this.http.get('/api/users/me', {withCredentials: true}).subscribe(
         (response) => {
            this.user = response as User
            console.log(this.user)
            this.logged = true
         },
         (error) => {
            if (error.status != 404) {
               console.error('Error when asking if logged: ' + JSON.stringify(error))
            }
         }
      )
   }

   register(username: string, email: string, pass: string) {
      this.http.post('/api/users/new', {username: username, email: email, password: pass}, {withCredentials: true}).subscribe(
         (response) => console.log('Usuario registrado con éxito'),
         (error) => alert('Error al registrar el usuario')
      )
   }

   logIn(user: string, pass: string) {
      console.log(user)
      console.log(pass)
      this.http.post('/api/auth/login', {username: user, password: pass}, {withCredentials: true}).subscribe(
         (response) => this.reqIsLogged(),
         (error) => alert('Wrong credentials')
      )
   }

   logOut() {
      return this.http.get('/api/auth/logout', {withCredentials: false}).subscribe((resp: any) => {
         console.log('LOGOUT: Successfully')
         this.logged = false
         this.user = undefined
      })
   }

   getProfilePic(): Observable<Blob> {
      return this.http.get('api/users/profilePic/image', {responseType: 'blob'})
   }

   changeProfilePic(image: File) {
      const formData = new FormData()
      formData.append('image', image)
      return this.http.put<User>(`/api/users/profilePic/image`, formData)
   }

   addPack(id: number) {
      return this.http.put<User>(`/api/users/addPack/${id}`, {withCredentials: true})
   }

   isLogged() {
      return this.logged
   }

   isAdmin() {
      return this.user && this.user.roles.indexOf('ADMIN') !== -1
   }

   currentUser() {
      return this.user
   }
}
