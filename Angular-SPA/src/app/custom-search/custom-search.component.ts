import { Component } from '@angular/core';
import { SearchService } from '../services/Search/search.service';
import { TagsService } from '../services/Tags/tags.service';

@Component({
  selector: 'app-custom-search',
  templateUrl: './custom-search.component.html',
  styleUrls: ['./custom-search.component.css']
})
export class CustomSearchComponent {

  constructor(private searchService:SearchService, private tags: TagsService) { }

  packsList: any[] = [];
  tagsList: any[] = [];
  tagsNames : String[] = [];
  

  ngOnInit (){

    this.searchService.getSearchPacks().subscribe((data: any) => {
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

}
