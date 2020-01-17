import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../api-call.service';
import { MatDialog } from '@angular/material';
import { ConfirmDialogModel, ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { DeleteItemsRequest} from '../entity/DeleteItemsRequest';

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
		if(this.selectedItems.length > 0 && this.selectedItems[0].deliveryId != item.deliveryId)
		{
			event.preventDefault();
		}
	}

	public itemCheckBoxSelected(currentItem)
	{
		let checked = (event.target as any).checked;
		let indexToRemove = -1;

		if(checked)
		{
			this.removeButton.disabled = false;
			this.selectedItems.push(currentItem);
		}
		else
		{
			indexToRemove = this.selectedItems.findIndex(item => item.itemId == currentItem.itemId);

			if(indexToRemove != -1)
			{
				this.selectedItems.splice(indexToRemove, 1);
			}
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
			if(dialogResult)
			{
				this.handleItemRemoval();
			}
    	});
	}
	
	handleItemRemoval()
	{
		let deliveryIndex = 0;
		let indexToRemove = -1;
		let allDeliveries = this.openDeliveries.concat(this.futureDeliveries).concat(this.closedDeliveries);

		if(this.selectedItems.length == 1)
		{
			for(let i = 0; i < allDeliveries.length; i++)
			{
				deliveryIndex = i;
				indexToRemove = allDeliveries[i].items.findIndex(element => element.itemId == this.selectedItems[0].itemId);
				
				if(indexToRemove != -1)
					break;
			}
			if(indexToRemove != -1)
				this.removeSingleItem(allDeliveries, deliveryIndex, indexToRemove);
		}
		else
		{
			this.removeSeveralItems(allDeliveries, deliveryIndex, indexToRemove);
		}
	}

	removeSingleItem(allDeliveries, deliveryIndex, indexToRemove)
	{
		this.apiService.removeItemFromDelivery(this.selectedItems[0].itemId).subscribe((data)=>
	  	{
			let deliveryId = allDeliveries[deliveryIndex].deliveryId;
			let deliveryStatus = allDeliveries[deliveryIndex].status;

			if(deliveryStatus == 'Open')
			{
				deliveryIndex = this.openDeliveries.findIndex(element => element.deliveryId == deliveryId);
				this.openDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
			}
			else if(deliveryStatus == 'Closed')
			{
				deliveryIndex = this.closedDeliveries.findIndex(element => element.deliveryId == deliveryId);
				this.closedDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
			}
			else if(deliveryStatus == 'Future')
			{
				deliveryIndex = this.futureDeliveries.findIndex(element => element.deliveryId == deliveryId);
				this.futureDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
			}

			this.selectedItems.length = 0;
			this.removeButton.disabled = true;
		});
	}

	removeSeveralItems(allDeliveries, deliveryIndex, indexToRemove)
	{
		let itemsToDeleteRequest = this.prepareDeleteItemBody();
		this.apiService.removeItemsFromDelivery(itemsToDeleteRequest).subscribe((data)=>
	  	{
			let deliveryId = allDeliveries[deliveryIndex].deliveryId;
			let deliveryStatus = allDeliveries[deliveryIndex].status;

			for(let item of itemsToDeleteRequest.items)
			{
				if(deliveryStatus == 'Open')
				{
					deliveryIndex = this.openDeliveries.findIndex(element => element.deliveryId == deliveryId);
					this.openDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
				}
				else if(deliveryStatus == 'Closed')
				{
					deliveryIndex = this.closedDeliveries.findIndex(element => element.deliveryId == deliveryId);
					this.closedDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
				}
				else if(deliveryStatus == 'Future')
				{
					deliveryIndex = this.futureDeliveries.findIndex(element => element.deliveryId == deliveryId);
					this.futureDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
				}
			}
			this.selectedItems.length = 0;
			this.removeButton.disabled = true;
		});
	}

	prepareDeleteItemBody()
	{
		let deleteJsonRequest = new DeleteItemsRequest(this.selectedItems);
		return deleteJsonRequest;
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
