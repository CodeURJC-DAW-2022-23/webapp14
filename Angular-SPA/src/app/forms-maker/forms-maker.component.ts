import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forms-maker',
  templateUrl: './forms-maker.component.html',
  styleUrls: ['./forms-maker.component.css']
})
export class FormsMakerComponent {

  constructor(private router:Router) { }

  formsPicsList:{[key: string]: string} = {
  '/assets/img/Forms-Icons/code.svg': 'code.svg',
  '/assets/img/Forms-Icons/ballings.svg': 'ballings.svg',
  '/assets/img/Forms-Icons/statistics.svg': 'statistics.svg',
  '/assets/img/Forms-Icons/thunder_icon.svg': 'thunder_icon.svg'
};

  getObjectKeys(obj: any) {
    return Object.keys(obj);
  }

   

}
