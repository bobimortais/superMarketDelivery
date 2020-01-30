import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private httpClient: HttpClient) { }
  
  public getItems(){
    return this.httpClient.get('/getItems');
  }

  public getDeliveries(){
    return this.httpClient.get('/getDeliveries');
  }

  public updateDelivery(deliveryInfo)
  {
    return this.httpClient.put('/updateDelivery', deliveryInfo);
  }

  public removeItemFromDelivery(itemId)
  {
    return this.httpClient.delete('/removeItemFromDelivery?itemId=' + itemId);
  }

  public removeItemsFromDelivery(itemsInfo)
  {
    return this.httpClient.post('/removeItemsFromDelivery', itemsInfo);
  }

  public addItemToDelivery(itemToAdd)
  {
    return this.httpClient.put('/addItemToDelivery', itemToAdd);
  }

  public getItem(itemId)
  {
    return this.httpClient.get('/getItems/' + itemId);
  }
}
