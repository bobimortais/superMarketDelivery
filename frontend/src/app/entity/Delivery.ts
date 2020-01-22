export class Delivery
{
    public deliveryId;
    public customerId;
    public customerName;
    public status;

    constructor(deliveryId: number, customerId: number, customerName: string, status: string) 
    { 
        this.deliveryId = deliveryId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.status = status;
    }
}