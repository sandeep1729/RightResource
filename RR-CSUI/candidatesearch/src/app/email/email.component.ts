import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'email-email',
  templateUrl: './email.component.html'
})
export class EmailComponent {

	onSubmit(form: NgForm){
		console.log(form);
	}
 
}