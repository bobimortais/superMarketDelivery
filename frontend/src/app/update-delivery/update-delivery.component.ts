import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-update-delivery',
  templateUrl: './update-delivery.component.html',
  styleUrls: ['./update-delivery.component.css']
})
export class UpdateDeliveryComponent implements OnInit {

  deliveryId: string;

  constructor(public dialogRef: MatDialogRef<UpdateDeliveryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UpdateDeliveryModel) {
    this.deliveryId = data.deliveryId;
  }

  ngOnInit() 
  {
  
  }

}

export class UpdateDeliveryModel {
 
  constructor(public deliveryId: string) 
  {
  }
}
