import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../api-call.service';

@Component({
  selector: 'app-super-market-app',
  templateUrl: './super-market-app.component.html',
  styleUrls: ['./super-market-app.component.css']
})

export class SuperMarketAppComponent implements OnInit {

  deliveries = [];
  
  constructor(private apiService: ApiCallService) { }


  ngOnInit() 
  {
	  this.apiService.getDeliveries().subscribe((data)=>{
      console.log(data);
	  this.deliveries = data['deliveryList'];
	  });
  }

}
