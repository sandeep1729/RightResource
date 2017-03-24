import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'add-mailbox',
  templateUrl: './addmailbox.component.html'
  })
export class AddMailboxComponent{

  onSubmit(form: NgForm){
		console.log(form);
	}
}
