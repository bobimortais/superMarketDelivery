import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../api-call.service';
import { MatDialog } from '@angular/material';
import { ConfirmDialogModel, ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { DeleteItemsRequest} from '../entity/DeleteItemsRequest';
import { AddItemComponent, AddItemModel } from '../add-item/add-item.component';
import { UpdateDeliveryComponent, UpdateDeliveryModel } from '../update-delivery/update-delivery.component';

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
  cancelButton;
  addButton;
	
  constructor(private apiService: ApiCallService, public dialog: MatDialog) { }


  	ngOnInit() 
  	{
	  this.removeButton = document.getElementById("removeButton") as any;
	  this.editButton = document.getElementById("editButton") as any;
	  this.addButton = document.getElementById("addButton") as any;
	  this.cancelButton = document.getElementById("cancelButton") as any;

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
		if((event.target as any).checked)
		{
			this.removeButton.disabled = false;
			this.selectedItems.push(currentItem);
		}
		else
		{
			let indexToRemove = this.selectedItems.findIndex(item => item.itemId == currentItem.itemId);

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
				this.handleItemRemoval();
    	});
	}
	
	handleItemRemoval()
	{
		let allDeliveries = this.openDeliveries.concat(this.futureDeliveries).concat(this.closedDeliveries);
		if(this.selectedItems.length == 1)
		{
			let deliveryIndex = 0;
			let indexToRemove = -1;
			
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
			this.removeSeveralItems(allDeliveries);
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
				this.totalPrice(this.openDeliveries[deliveryIndex]);
			}
			else if(deliveryStatus == 'Closed')
			{
				deliveryIndex = this.closedDeliveries.findIndex(element => element.deliveryId == deliveryId);
				this.closedDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
				this.totalPrice(this.closedDeliveries[deliveryIndex]);
			}
			else if(deliveryStatus == 'Future')
			{
				deliveryIndex = this.futureDeliveries.findIndex(element => element.deliveryId == deliveryId);
				this.futureDeliveries[deliveryIndex].items.splice(indexToRemove, 1);
				this.totalPrice(this.futureDeliveries[deliveryIndex]);
			}

			this.selectedItems.length = 0;
			this.removeButton.disabled = true;
		});
	}

	removeSeveralItems(allDeliveries)
	{
		let itemsToDeleteRequest = new DeleteItemsRequest(this.selectedItems);
		this.apiService.removeItemsFromDelivery(itemsToDeleteRequest).subscribe((data)=>
	  	{
			for(let item of this.selectedItems)
			{
				let itemId = item.itemId;
				let deliveryId = item.deliveryId;
				let deliveryIndex = allDeliveries.findIndex(element => element.deliveryId == deliveryId);
				let deliveryStatus = allDeliveries[deliveryIndex].status;
				let itemIndex = 0;

				if(deliveryStatus == 'Open')
				{
					deliveryIndex = this.openDeliveries.findIndex(element => element.deliveryId == deliveryId);
					itemIndex = this.openDeliveries[deliveryIndex].items.findIndex(element => element.itemId = itemId);
					this.openDeliveries[deliveryIndex].items.splice(itemIndex, 1);
					this.totalPrice(this.openDeliveries[deliveryIndex]);
				}
				else if(deliveryStatus == 'Closed')
				{
					deliveryIndex = this.closedDeliveries.findIndex(element => element.deliveryId == deliveryId);
					itemIndex = this.closedDeliveries[deliveryIndex].items.findIndex(element => element.itemId = itemId);
					this.closedDeliveries[deliveryIndex].items.splice(itemIndex, 1);
					this.totalPrice(this.closedDeliveries[deliveryIndex]);
				}
				else if(deliveryStatus == 'Future')
				{
					deliveryIndex = this.futureDeliveries.findIndex(element => element.deliveryId == deliveryId);
					itemIndex = this.futureDeliveries[deliveryIndex].items.findIndex(element => element.itemId = itemId);
					this.futureDeliveries[deliveryIndex].items.splice(itemIndex, 1);
					this.totalPrice(this.futureDeliveries[deliveryIndex]);
				}
			}
			this.selectedItems.length = 0;
			this.removeButton.disabled = true;
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
			this.cancelButton.disabled = false;
		}
		else if(checked == false && this.selectedDelivery == currentCheckedDelivery)
		{
			this.selectedDelivery = null;
			this.editButton.disabled = true;
			this.addButton.disabled = true;
			this.cancelButton.disabled = true;
		}
	}

	openItemAdditionForm()
	{
		const dialogData = new AddItemModel(this.selectedDelivery);
		const dialogRef = this.dialog.open(AddItemComponent, 
		{
		  width: '450px',
		  height: '200px',
		  data: dialogData
		});
	}

	openUpdateDeliveryForm()
	{
		const dialogData = new UpdateDeliveryModel(this.selectedDelivery);
		const dialogRef = this.dialog.open(UpdateDeliveryComponent, 
		{
		  width: '450px',
		  height: '200px',
		  data: dialogData
		});
	}

	openCancelDeliveryForm()
	{
		const message = "Do you want to cancel delivery " + this.selectedDelivery + " ?";
    	const dialogData = new ConfirmDialogModel("Confirm removal", message);
 
		const dialogRef = this.dialog.open(ConfirmDialogComponent, 
		{
      		width: '450px',
			height: '200px',
      		data: dialogData
    	});
	}

	handleItemAddition(itemToAdd)
	{
		this.apiService.addItemToDelivery(itemToAdd).subscribe((data)=>
	  	{
			this.openDeliveries[0].items.push(itemToAdd);
		});
	}

}
