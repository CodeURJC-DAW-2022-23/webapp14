import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { Routes, RouterModule} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HomePageComponentComponent } from './home-page-component/home-page-component.component';
import { PacksComponent } from './packs/packs.component';
import { FormsComponent } from './forms/forms.component';
import { SurveysComponent } from './surveys/surveys.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { HeaderLoggedComponent } from './header-logged/header-logged.component';


const routes: Routes = [
  {path: '', component: HomePageComponentComponent},
  {path: 'packs', component: PacksComponent},
  {path: 'forms', component: FormsComponent},
  {path: 'surveys', component: SurveysComponent},
  
 {path: '**', component: ErrorPageComponent} //path de error siempre el último*/
]


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomePageComponentComponent,
    PacksComponent,
    FormsComponent,
    SurveysComponent,
    ErrorPageComponent,
    HeaderLoggedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
