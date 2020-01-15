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
import { MatButtonModule, MatDialogModule } from '@angular/material';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { AddItemComponent } from './add-item/add-item.component';

@NgModule({
  declarations: [
    AppComponent,
    SuperMarketAppComponent,
    ConfirmDialogComponent,
    AddItemComponent
  ],
  imports: [
    BrowserModule,
	MatExpansionModule, 
	MatInputModule,
	BrowserAnimationsModule,
  HttpClientModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatDialogModule
  ],
  providers: [],
  entryComponents: [ConfirmDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
