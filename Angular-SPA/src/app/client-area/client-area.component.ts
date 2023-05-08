import {Component} from '@angular/core'
import {ClientService} from '../services/Images/clientArea.service'
import {User} from '../Model/users.model'
import {FormsService} from '../services/Forms/forms.service'
import {UsersService} from '../services/Users/users.service'
import {HttpClient} from '@angular/common/http'
import {Router} from '@angular/router'

@Component({
   selector: 'app-client-area',
   templateUrl: './client-area.component.html',
   styleUrls: ['./client-area.component.css'],
})
export class ClientAreaComponent {
   packsList?: any[] = []
   tagsList: any[] = []
   formsList: any[] = []
   tagsNames: String[] = []
   user?: User
   isAdmin?: boolean = false
   username?: string
   pack: any
   image: string | null = null

   sendImage: File | null = null

   imagesObject: {[key: string]: string} = {
      '/static/img/Forms-Icons/code.svg': 'assets/img/Forms-Icons/code.svg',
      '/static/img/Forms-Icons/ballings.svg': 'assets/img/Forms-Icons/ballings.svg',
      '/static/img/Forms-Icons/statistics.svg': 'assets/img/Forms-Icons/statistics.svg',
      '/static/img/Forms-Icons/thunder_icon.svg': 'assets/img/Forms-Icons/thunder_icon.svg',
   }

   constructor(private clientService: ClientService, private forms: FormsService, private userService: UsersService, private http: HttpClient, private router: Router) {}

   ngOnInit() {
      this.user = this.userService.currentUser()
      console.log('packList:', this.user?.packList)
      this.packsList = this.user?.packList
      this.username = this.user?.username

      this.getImage()
      this.getForms()
      this.getTags()

      this.isAdmin = this.userService.isAdmin()
   }

   onSubmit() {
      if (this.sendImage) {
         const formData = new FormData()
         formData.append('image', this.sendImage)
         this.http.put('/api/users/profilePic/image', formData).subscribe(
            (response) => {
               console.log(response)
            },
            (error) => {
               console.error(error)
            }
         )
      }
   }

   handleFileInput(event: Event) {
      const target = event.target as EventTarget
      if (target instanceof HTMLInputElement) {
         this.sendImage = target.files![0]
      }
   }

   deleteForm(id: number) {
      console.log(id)
      this.forms.deleteForm(id).subscribe((data: any) => {
         console.log(data)
      })
      this.getForms()
      this.router.navigate(['forms'])
   }

   onPackClick(pack: any) {
      this.router.navigate(['packInfo'], {state: {pack}})
   }

   getForms() {
      this.forms.getForms().subscribe((data: any) => {
         this.formsList = data
      })
   }

   getTags() {
      this.clientService.getTags().subscribe((data: any) => {
         this.tagsList = data
         console.log(this.tagsList)
      })

      this.tagsList.forEach((element) => {
         this.tagsNames.push(element.tagsName)
      })
   }

   getObjectKeys(obj: any) {
      return Object.keys(obj)
   }

   isPackInTag(packTitle: string, tag: any): boolean {
      return tag.packs.findIndex((p: any) => p.packTitle === packTitle) !== -1
   }

   getImage() {
      this.userService.getProfilePic().subscribe((data: Blob) => {
         console.log(data)
         if (data.size != 0) {
            const reader = new FileReader()
            reader.onload = () => {
               const arrayBuffer = reader.result as ArrayBuffer
               const uint8Array = new Uint8Array(arrayBuffer)
               const charCodeArray = Array.from(uint8Array)
               const base64String = btoa(String.fromCharCode(...charCodeArray))
               this.image = 'data:image/jpeg;base64,' + base64String
            }
            reader.readAsArrayBuffer(data)
         } else {
            this.image = 'assets/img/Profile-Pics/profile_img.png'
         }
      })
   }
}
