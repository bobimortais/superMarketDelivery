import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  deliveryId: string;

  constructor(public dialogRef: MatDialogRef<AddItemComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AddItemModel) {
    // Update view with given values
    this.deliveryId = data.deliveryId;
  }

  ngOnInit() 
  {
  
  }

}

export class AddItemModel {
 
  constructor(public deliveryId: string) 
  {
  }
}
