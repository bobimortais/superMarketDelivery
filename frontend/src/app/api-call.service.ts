import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private httpClient: HttpClient) { }
  
  public getItems(){
    return this.httpClient.get('http://localhost:8080/getItems');
  }

  public getDeliveries(){
    return this.httpClient.get('http://localhost:8080/getDeliveries');
  }

  public removeItemFromDelivery(itemId)
  {
    return this.httpClient.delete('http://localhost:8080/removeItemFromDelivery?itemId=' + itemId);
  }

  public removeItemsFromDelivery(itemsInfo)
  {
    return this.httpClient.post('http://localhost:8080/removeItemsFromDelivery', itemsInfo);
  }

  public addItemToDelivery(itemToAdd)
  {
    return this.httpClient.post('http://localhost:8080/addItemToDelivery', itemToAdd);
  }
}
