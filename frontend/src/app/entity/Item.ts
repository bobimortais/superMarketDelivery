export class Item
{
    itemCode: number;
    name: string;
    brand: string;
    description: string;
    price: number;
    constructor(itemCode: number, name: string, brand: string, description: string, price: number) 
    { 
        this.itemCode = itemCode;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
    }
}