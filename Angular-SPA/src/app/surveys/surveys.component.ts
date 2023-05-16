import { Component } from '@angular/core';

@Component({
  selector: 'app-surveys',
  templateUrl: './surveys.component.html',
  styleUrls: ['./surveys.component.css']
})
export class SurveysComponent {

  toggleSurvey(id: number): void {
    const content = document.getElementById(`survey${id}-content`);

    if (content && content.style.display === 'none') {
      content.style.display = 'block';
    } else if (content) {
      content.style.display = 'none';
    }
  }



}
