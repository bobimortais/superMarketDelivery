import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../api-call.service';

@Component({
  selector: 'app-super-market-app',
  templateUrl: './super-market-app.component.html',
  styleUrls: ['./super-market-app.component.css']
})

export class SuperMarketAppComponent implements OnInit {

  public openDeliveries = [];
  public closedDeliveries = [];
  public futureDeliveries = [];
  
  constructor(private apiService: ApiCallService) { }

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
		let removeButton = document.getElementById("removeButton");
		
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
		console.log("Item checkbox selected");
	}

	public deliveryCheckBoxSelected()
	{

	}
}
