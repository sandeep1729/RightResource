import { Component} from '@angular/core';
import { NgForm} from '@angular/forms';
@Component({
  selector: 'req-requirement',
  templateUrl: './requirement.component.html'
})
export class RequirementComponent{
	onSubmit(form: NgForm){
		console.log(form);
	}
 

}
