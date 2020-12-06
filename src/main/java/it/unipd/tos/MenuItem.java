////////////////////////////////////////////////////////////////////
// SARA PRIVITERA 1201157
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

public class MenuItem
{
    ItemType type;
    String name;
    double price;

    public MenuItem(ItemType type, String name, double price)
    {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public ItemType getType() { return type; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

}