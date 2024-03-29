import { Component } from '@angular/core';
import { PacksService } from '../services/Packs/packs.service';
import { TagsService } from '../services/Tags/tags.service';
import { Router } from '@angular/router';
import { UsersService } from '../services/Users/users.service';

@Component({
  selector: 'app-packs',
  templateUrl: './packs.component.html',
  styleUrls: ['./packs.component.css']
})


export class PacksComponent {
  constructor(private packs: PacksService, private tags: TagsService, private router: Router, private userService: UsersService){}

  packsList: any[] = [];
  tagsList: any[] = [];
  tagsNames : String[] = [];

  numPacksToShow = 0;

  ngOnInit(): void {
    this.packs.getPacks().subscribe((data: any) => {
      this.packsList = data;
      console.log(this.packsList);
    })

    this.tags.getTags().subscribe((data: any) => {
      this.tagsList = data;
      console.log(this.tagsList);
    })

     this.tagsList.forEach(element => {
      this.tagsNames.push(element.tagsName);
   });
  }

   isPackInTag(packTitle: string, tag: any): boolean {
     return tag.packs.findIndex((p: any) => p.packTitle === packTitle) !== -1;
    }

     showMorePacks(): void {
      this.numPacksToShow += 4;
     }

     showLessPacks(): void {
      if (this.numPacksToShow >= 4)
            this.numPacksToShow -= 4;
     }
 
     onPackClick(pack: any) {
      if (this.userService.isLogged()){
          this.router.navigate(['packInfo'], { state: { pack } });
      } else {
          this.router.navigate(['login']);
      }
        
    }

}
