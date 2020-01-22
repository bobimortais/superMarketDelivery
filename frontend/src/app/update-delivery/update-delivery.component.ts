import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Delivery } from '../entity/Delivery';

@Component({
  selector: 'app-update-delivery',
  templateUrl: './update-delivery.component.html',
  styleUrls: ['./update-delivery.component.css']
})
export class UpdateDeliveryComponent implements OnInit {

  delivery: any;

  constructor(public dialogRef: MatDialogRef<UpdateDeliveryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UpdateDeliveryModel) {
    this.delivery = data;
  }

  ngOnInit() 
  {
  
  }

}

export class UpdateDeliveryModel {
 
  public deliveryId;
  public customerId;
  public customerName;
  public status;

  constructor(delivery: any) 
  {
    console.log(delivery.deliveryId);
    console.log(delivery.customerId);
    console.log(delivery.customerName);
    console.log(delivery.status);

    this.deliveryId = delivery.deliveryId;
    this.customerId = delivery.customerId;
    this.customerName = delivery.customerName;
    this.status = delivery.status;
  }
}
