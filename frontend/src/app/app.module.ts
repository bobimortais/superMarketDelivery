import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { SuperMarketAppComponent } from './super-market-app/super-market-app.component';
import {MatExpansionModule, MatInputModule, MatSelectModule} from '@angular/material'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule, MatDialogModule } from '@angular/material';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { AddItemComponent } from './add-item/add-item.component';
import { UpdateDeliveryComponent } from './update-delivery/update-delivery.component';

@NgModule({
  declarations: [
    AppComponent,
    SuperMarketAppComponent,
    ConfirmDialogComponent,
    AddItemComponent,
    UpdateDeliveryComponent
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
  MatDialogModule,
  MatSelectModule
  ],
  providers: [],
  entryComponents: [
    ConfirmDialogComponent, 
    AddItemComponent,
    UpdateDeliveryComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
