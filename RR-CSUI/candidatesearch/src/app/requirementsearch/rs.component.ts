import { Component, OnInit} from '@angular/core';
import { Http, Response } from '@angular/http';
import { User } from './user.interface';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Component({
  selector: 'rs',
  templateUrl: 'rs.component.html',
})
export class RsComponent implements OnInit {
  public user: User;

  private candidatesearch = 'http://localhost:4200/candidate/search' ;
  ngOnInit() {
    this.user = {
      name: '',
        address: {
         street: '',
        
      }
    };
  }


  
  save(model: User, isValid: boolean) {
    console.log(model, isValid);
    console.log(this.candidatesearch);
    console.log("name" + this.user.name);

   // <!-- return this.http.get(this.candidatesearch)
   //         .map((res:Response) => console.log(res))
   //         .subscribe((err) => this.handleError); 

  }

  private handleError(error: any){
    let errMsg = (error.message) ? error.message : (error.status ? `%{error.status} - ${error.statusText}` : 'ServerError');
    alert(errMsg);
    return Observable.throw(errMsg);
  }

  constructor(private http: Http){}
}