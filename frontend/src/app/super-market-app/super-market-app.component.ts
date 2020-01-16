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
  public selectedItems = [];
  selectedDelivery;
  checked = false;
  removeButton;
  editButton;
  addButton;
	
  constructor(private apiService: ApiCallService, public dialog: MatDialog) { }


  ngOnInit() 
  {
	  this.removeButton = document.getElementById("removeButton") as any;
	  this.editButton = document.getElementById("editButton") as any;
	  this.addButton = document.getElementById("addButton") as any;

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

	blockItemSelectionFromDifferentDeliveries(event: Event, item) 
	{
		console.log("item on click" + item);
		let deliveryIndex = 0;
		let indexToRemove = -1;
		for(let i = 0; i < this.openDeliveries.length; i++)
		{
			deliveryIndex = i;
			indexToRemove = this.openDeliveries[i].items.findIndex(element => element.itemId == this.selectedItems[0]);
			
			if(indexToRemove != -1)
				break;
		}
	}

	public itemCheckBoxSelected(currentItemId, deliveryId)
	{
		let checked = (event.target as any).checked;
		let indexToRemove = -1;

		if(checked)
		{
			this.removeButton.disabled = false;
			this.selectedItems.push(currentItemId);
		}
		else
		{
			indexToRemove = this.selectedItems.findIndex(itemId => itemId == currentItemId);

			if(indexToRemove != -1)
			{
				this.selectedItems.splice(indexToRemove, 1);
			}

			console.log(this.selectedItems.length);
			if(this.selectedItems.length == 0)
			{
				this.selectedItems = [];
				this.removeButton.disabled = true;
			}
		}
	}

	confirmDialog(): void 
	{
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
			console.log("dialogResult" + dialogResult);
			if(dialogResult)
			{
				console.log("Entrou handleItemRemoval");
				this.handleItemRemoval();
			}
    	});
	}
	
	handleItemRemoval()
	{
		let deliveryIndex = 0;
		let indexToRemove = -1;

		console.log("this.selectedItems[0] :" + this.selectedItems[0]);

		for(let i = 0; i < this.openDeliveries.length; i++)
		{
			deliveryIndex = i;
			indexToRemove = this.openDeliveries[i].items.findIndex(element => element.itemId == this.selectedItems[0]);
			
			if(indexToRemove != -1)
				break;
		}


		console.log("indexToRemove :" + indexToRemove);

		if(indexToRemove != -1)
		{
			this.apiService.removeItemFromDelivery(this.selectedItems[0]).subscribe((data)=>
	  		{
				this.openDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
				this.removeButton.disabled = true;
			});
		}
	}

	handleItemAddition(itemToAdd)
	{
		this.apiService.addItemToDelivery(itemToAdd).subscribe((data)=>
	  	{
			this.openDeliveries[0].items.push(itemToAdd);
		});
	}
	
	blockCollapseOfDelivery(event: Event, currentCheckedDelivery) 
	{
		event.stopPropagation();

		if(this.selectedDelivery != null && currentCheckedDelivery != this.selectedDelivery)
		{
			event.preventDefault();
		}
	}

	public deliverySelected(currentCheckedDelivery)
	{
		let checked = (event.target as any).checked;

		if(checked == true && this.selectedDelivery == null)
		{
			this.selectedDelivery = currentCheckedDelivery;
			this.editButton.disabled = false;
			this.addButton.disabled = false;
		}
		else if(checked == false && this.selectedDelivery == currentCheckedDelivery)
		{
			this.selectedDelivery = null;
			this.editButton.disabled = true;
			this.addButton.disabled = true;
		}
	}

}
