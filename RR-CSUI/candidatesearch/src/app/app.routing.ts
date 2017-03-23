import { Routes, RouterModule } from "@angular/router";

import { EmailComponent } from "./email/email.component";
import { CandidateComponent } from "./candidate/candidate.component";
import { RequirementComponent } from "./requirement/requirement.component";
import { CsComponent } from "./candidatesearch/cs.component";
import { RsComponent } from "./requirementsearch/rs.component";
import { HomeComponent } from "./home/home.component";


const APP_ROUTES: Routes = [

 	{ path: 'email', component: EmailComponent },
 	{ path: 'candidate', component: CandidateComponent },
 	{ path: 'requirement', component: RequirementComponent},
 	{ path: 'candidatesearch', component: CsComponent},
 	{ path: 'requirementsearch', component: RsComponent},
 	{ path: 'home', component: HomeComponent},

];


export const routing = RouterModule.forRoot(APP_ROUTES);
