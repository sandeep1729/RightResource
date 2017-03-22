import { Routes, RouterModule } from "@angular/router";

import { EmailComponent } from "./email/email.component";
import { CandidateComponent } from "./candidate/candidate.component";
import { RequirementComponent } from "./requirement/requirement.component";

const APP_ROUTES: Routes = [
	{ path: '', redirectTo: '/candidateSearch', pathMatch: 'full'},
 	{ path: 'email', component: EmailComponent },
 	{ path: 'candidate', component: CandidateComponent },
 	{ path: 'requirement', component: RequirementComponent}
];


export const routing = RouterModule.forRoot(APP_ROUTES);
