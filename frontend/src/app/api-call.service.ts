import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private httpClient: HttpClient) { }
  
  public getDeliveries(){
    return this.httpClient.get('http://localhost:8080/getDeliveries');
  }

  public removeItemFromDelivery(itemId)
  {
    return this.httpClient.delete('http://localhost:8080/removeItemFromDelivery?itemId=' + itemId);
  }

  public addItemToDelivery(itemToAdd)
  {
    return this.httpClient.post('http://localhost:8080/addItemToDelivery', itemToAdd);
  }
}
