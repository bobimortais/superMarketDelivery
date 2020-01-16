export class DeleteItemsRequest
{
    public items = new Array();
    constructor(itemRequest) 
    { 
        this.items = new Array();
        this.items = this.items.concat(itemRequest.map(item => item.itemId));
    }
}