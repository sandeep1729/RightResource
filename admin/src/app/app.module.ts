import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { CandidateComponent } from './candidate/candidate.component';
import { EmailComponent } from './email/email.component';
import { RequirementComponent } from './requirement/requirement.component';
import { HeaderComponent } from './header.component';
import  { routing } from "./app.routing";


@NgModule({
  declarations: [
    AppComponent,
    CandidateComponent,
    EmailComponent,
    RequirementComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
