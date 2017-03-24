import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'add-user',
  templateUrl: './adduser.component.html'
  })
export class AddUserComponent{

  onSubmit(form: NgForm){
		console.log(form);
	}
}
