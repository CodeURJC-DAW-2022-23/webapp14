import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../services/Search/search.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router: Router, private searchService: SearchService) { }
      
    

  
      onSearchSubmit(searchInput: HTMLInputElement){

        const query = searchInput.value;
        this.searchService.setQuery(query);
        this.router.navigate(['search']);

      }
  
}
