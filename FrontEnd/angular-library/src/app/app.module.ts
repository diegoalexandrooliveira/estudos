import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SharedLibraryModule } from 'shared-library';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SharedLibraryModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
