export class DeliveryItem
{
    itemCode: number;
    deliveryId: number;
    constructor(itemCode: number, deliveryId: number) 
    { 
        this.itemCode = itemCode;
        this.deliveryId = deliveryId;
    }
}