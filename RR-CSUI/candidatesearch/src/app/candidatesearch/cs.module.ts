import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CsComponent } from './cs.component';




@NgModule({
  declarations: [
    CsComponent,
    ],
    
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [CsComponent]
})
export class AppModule { }
