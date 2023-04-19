import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HomePageComponentComponent } from './home-page-component/home-page-component.component';
import { PacksComponent } from './packs/packs.component';
import { FormsComponent } from './forms/forms.component';
import { SurveysComponent } from './surveys/surveys.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { HeaderLoggedComponent } from './header-logged/header-logged.component';
import { PacksService } from './services/Packs/packs.service';
import { TagsService } from './services/Tags/tags.service';
import { FormsService } from './services/Forms/forms.service';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ReactiveFormsModule } from '@angular/forms';



const routes: Routes = [
  { path: '', component: HomePageComponentComponent },
  { path: 'packs', component: PacksComponent },
  { path: 'forms', component: FormsComponent },
  { path: 'surveys', component: SurveysComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signUp', component: SignUpComponent },
  { path: '**', component: ErrorPageComponent } // path de error siempre el último
];

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
    HeaderLoggedComponent,
    LoginComponent,
    SignUpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    PacksService,
    TagsService,
    FormsService,
    RouterModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
