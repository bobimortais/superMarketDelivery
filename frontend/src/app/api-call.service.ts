import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private httpClient: HttpClient) { }
  
  public getItems(){
    return this.httpClient.get('http://localhost:8081/getItems');
  }

  public getDeliveries(){
    return this.httpClient.get('http://localhost:8081/getDeliveries');
  }

  public updateDelivery(deliveryInfo)
  {
    return this.httpClient.put('http://localhost:8081/updateDelivery', deliveryInfo);
  }

  public removeItemFromDelivery(itemId)
  {
    return this.httpClient.delete('http://localhost:8081/removeItemFromDelivery?itemId=' + itemId);
  }

  public removeItemsFromDelivery(itemsInfo)
  {
    return this.httpClient.post('http://localhost:8081/removeItemsFromDelivery', itemsInfo);
  }

  public addItemToDelivery(itemToAdd)
  {
    return this.httpClient.put('/addItemToDelivery', itemToAdd);
  }

  public getItem(itemId)
  {
    return this.httpClient.get('http://localhost:8081/getItems/' + itemId);
  }
}
