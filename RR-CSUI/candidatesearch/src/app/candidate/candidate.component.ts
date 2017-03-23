import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'cand-candidate',
  templateUrl: './candidate.component.html'
  })
export class CandidateComponent{

  onSubmit(form: NgForm){
		console.log(form);
	}
}
