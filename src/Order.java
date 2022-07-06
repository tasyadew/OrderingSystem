/*
*  Name         : Natasya Nadhira Binti Ahmad Nazrain (A20EC0103)
*  Section      : 12
*  Instructor   : Dr. Arafat Mohammed Rashad Al-dhaqm
*  Last Modified: 5 July 2022, 1:37 p.m.
*
*  Brief Description: 
*  Order class is used as an array of order objects that will copy 
*  food or drinks object.
*/


public class Order {
    int id;
    String name;
    double price;
    int quantity;
    String variation;

    //Default constructor
    Order(){
        id = 0;
        name = null;
        price = 0;
        quantity = 0; 
        variation = null;
    }

    //Parameterised constructor
    Order(int id, String name, double price, int quantity, String variation){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.variation = variation;
    }

    //Copy constructor (overload)
    Order(Food right){
        this.id = right.getId();
        this.name = right.getName();
        this.price = right.getPrice();
        this.quantity = right.getQuantity();
        this.variation = right.getVariation();
    }

    //Copy constructor (overload)
    Order(Drinks right){
        this.id = right.getId();
        this.name = right.getName();
        this.price = right.getPrice();
        this.quantity = right.getQuantity();
        this.variation = right.getVariation();
    }

    //Mutator
    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setPrice(double price){ this.price = price; }
    public void setQuantity(int quantity){ this.quantity = quantity; }
    public void setVariation(String variation){ this.variation = variation; }

    //Accessor
    public int getId(){ return id; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public int getQuantity(){ return quantity; }
    public String getVariation(){ return variation; }
}