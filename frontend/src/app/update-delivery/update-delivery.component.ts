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
    @Inject(MAT_DIALOG_DATA) public data: UpdateDeliveryModel) 
    {
    this.delivery = data.delivery;
  }

  ngOnInit() 
  {
  
  }

}

export class UpdateDeliveryModel {

  constructor(public delivery: any) 
  {
  }
}
