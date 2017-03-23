import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RsComponent } from './rs.component';




@NgModule({
  declarations: [
    RsComponent,
    ],
    
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [RsComponent]
})
export class AppModule { }
