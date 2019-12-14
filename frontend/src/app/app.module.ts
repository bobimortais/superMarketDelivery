import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { SuperMarketAppComponent } from './super-market-app/super-market-app.component';
import {MatExpansionModule, MatInputModule} from '@angular/material'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    SuperMarketAppComponent
  ],
  imports: [
    BrowserModule,
	MatExpansionModule, 
	MatInputModule,
	BrowserAnimationsModule,
	HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
