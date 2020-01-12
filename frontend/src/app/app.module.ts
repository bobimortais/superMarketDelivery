import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { SuperMarketAppComponent } from './super-market-app/super-market-app.component';
import {MatExpansionModule, MatInputModule} from '@angular/material'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { MdIconModule } from 

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
  HttpClientModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
