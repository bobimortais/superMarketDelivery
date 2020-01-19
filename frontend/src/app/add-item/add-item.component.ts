import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';
import { ApiCallService } from '../api-call.service';
import { Item } from '../entity/Item';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  deliveryId: string;

  constructor(private apiService: ApiCallService, public dialogRef: MatDialogRef<AddItemComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AddItemModel) {
    // Update view with given values
    this.deliveryId = data.deliveryId;
  }

  items = [];

  ngOnInit() 
  {
    this.apiService.getItems().subscribe((data)=>
	  {
		  for(var val of data['items'])
	  	{
        let item = new Item(val.itemCode, val.name, val.brand, val.description, val.price);
        this.items.push(item);
		  	console.log(val);
		  }
	  });
  
  }

}

export class AddItemModel {
 
  constructor(public deliveryId: string) 
  {
  }
}
