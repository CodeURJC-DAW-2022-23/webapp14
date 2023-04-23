import { Component } from '@angular/core';
import { FormsService } from '../services/Forms/forms.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.css']
})
export class FormsComponent {

  constructor( private forms: FormsService, private router: Router) {}

  userLogged: boolean = false;
  formsList: any[] = [];
  imagesObject:{[key: string]: string} = {
  '/static/img/Forms-Icons/code.svg': '/assets/img/Forms-Icons/code.svg',
  '/static/img/Forms-Icons/ballings.svg': '/assets/img/Forms-Icons/ballings.svg',
  '/static/img/Forms-Icons/statistics.svg': '/assets/img/Forms-Icons/statistics.svg',
  '/static/img/Forms-Icons/thunder_icon.svg': '/assets/img/Forms-Icons/thunder_icon.svg'
};


  ngOnInit(): void {
    this.forms.getForms().subscribe((data: any) => {
      this.formsList = data;
      console.log(this.formsList);
    })
  }

  getObjectKeys(obj: any) {
    return Object.keys(obj);
  }


   onFormClick(form: any) {
        this.router.navigate(['post'], { state: { form } });
    }

    onFormMakerClick() {
        this.router.navigate(['formsMaker']);
    }


}
