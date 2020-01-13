import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../api-call.service';
import { MatDialog } from '@angular/material';
import { ConfirmDialogModel, ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-super-market-app',
  templateUrl: './super-market-app.component.html',
  styleUrls: ['./super-market-app.component.css']
})

export class SuperMarketAppComponent implements OnInit {

  public openDeliveries = [];
  public closedDeliveries = [];
	public futureDeliveries = [];
	result: string = '';
  
  constructor(private apiService: ApiCallService, public dialog: MatDialog) { }

	checked = false;
	isChildItemSelected: boolean = false;

  ngOnInit() 
  {
	  this.apiService.getDeliveries().subscribe((data)=>
	  {
		console.log(data);
		for(var val of data['deliveries'])
		{
			this.totalPrice(val);
			if(val['status'] == 'Open')
			{
				this.openDeliveries.push(val);
			}
			else if(val['status'] == 'Closed')
			{
				this.closedDeliveries.push(val);
			}
			else if(val['status'] == 'Future')
			{
				this.futureDeliveries.push(val);
			}
		}
	  });
  }

	private totalPrice(val)
	{
		val.totalPrice = val.items.map(t => t.price).reduce((a, b) => a + b, 0).toFixed(2);
	}

	public itemCheckBoxSelected()
	{
		var itemId = event.srcElement.parentElement.parentElement.parentElement.id;
		console.log(itemId);
		let removeButton = document.getElementById("removeButton") as any;
		
		if(!this.isChildItemSelected)
		{
			removeButton.disabled = false;
			this.isChildItemSelected = true;
		}
		else
		{
			removeButton.disabled = true;
			this.isChildItemSelected = false;
		}
	}

	public deliveryCheckBoxSelected()
	{

	}

	confirmDialog(): void {
    const message = "Do you want to remove the selected items?";
 
    const dialogData = new ConfirmDialogModel("Confirm removal", message);
 
		const dialogRef = this.dialog.open(ConfirmDialogComponent, 
		{
      width: '450px',
			height: '200px',
      data: dialogData
    });
 
		dialogRef.afterClosed().subscribe(dialogResult => 
		{
			this.result = dialogResult;
			console.log("Result: " + this.result);

			if(dialogResult)
			{
				this.handleItemRemoval();
			}
    });
	}
	
	handleItemRemoval()
	{
		this.openDeliveries.splice(0, 1);
	}
}
