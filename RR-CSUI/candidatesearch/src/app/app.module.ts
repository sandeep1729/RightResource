import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Angular2FontawesomeModule } from 'angular2-fontawesome/angular2-fontawesome';


import { AppComponent } from './app.component';
import { CandidateComponent } from './candidate/candidate.component';
import { EmailComponent } from './email/email.component';
import { RequirementComponent } from './requirement/requirement.component';
import { HeaderComponent } from './header.component';
import  { routing } from "./app.routing";
import { CsComponent } from './candidatesearch/cs.component';
import { RsComponent } from './requirementsearch/rs.component';
import { HomeComponent } from './home/home.component';
import { ReportsComponent } from './reports/reports.component';
import { AddMailboxComponent } from './addmailbox/addmailbox.component';
import { AddUserComponent} from './adduser/adduser.component';


@NgModule({
  declarations: [
    AppComponent,
    CandidateComponent,
    EmailComponent,
    RequirementComponent,
    HeaderComponent,
    CsComponent,
    RsComponent,
    HomeComponent,
    ReportsComponent,
    AddMailboxComponent,
    AddUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    Angular2FontawesomeModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
