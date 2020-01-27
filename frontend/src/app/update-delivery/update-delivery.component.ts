import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-delivery',
  templateUrl: './update-delivery.component.html',
  styleUrls: ['./update-delivery.component.css']
})
export class UpdateDeliveryComponent implements OnInit {

  delivery: any;
  public defaultStatus;

  constructor(public dialogRef: MatDialogRef<UpdateDeliveryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UpdateDeliveryModel) 
    {
    this.delivery = data.delivery;
    this.defaultStatus = data.delivery.status;
    console.log(this.defaultStatus);
  }

  ngOnInit() 
  {
  
  }

  cancelUpdate(): void
  {
    this.dialogRef.close(true);
  }

  updateDelivery():void
  {
    this.dialogRef.close(true);
  }
}

export class UpdateDeliveryModel {

  constructor(public delivery: any) 
  {
    
  }
}
