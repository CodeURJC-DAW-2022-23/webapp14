import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TagsService } from '../services/Tags/tags.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-pack-info',
  templateUrl: './pack-info.component.html',
  styleUrls: ['./pack-info.component.css']
})
export class PackInfoComponent {
  pack: any;
  tagsList: any[] = [];
  tagsNames : String[] = [];
 
  private readonly DOWNLOAD_PDF_URL = '/api/pdf/download/';

  constructor(private activatedRoute: ActivatedRoute, private tags: TagsService, private http: HttpClient) {}

  ngOnInit() {
    this.pack = history.state.pack;
    console.log(this.pack); 

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

    downloadPdf(id: number): void {
    this.http.get(this.DOWNLOAD_PDF_URL + id, { responseType: 'blob' })
      .subscribe(response => {
        const blob = new Blob([response], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'material.pdf';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      });
  }


   


}
