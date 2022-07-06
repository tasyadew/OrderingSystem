/*
*  Name         : Safia Zulaikha Binti Zulhazmi (A20EC0140)
*  Section      : 12
*  Instructor   : Dr. Arafat Mohammed Rashad Al-dhaqm
*  Last Modified: 5 July 2022, 1:37 p.m.
*
*  Brief Description: 
*  Food class is an abstract class and the parent class of Burger class
*  and Chicken class.
*/


public abstract class Food{
    int id;
    String name;
    double price;
    int quantity;
    String variation;

    //Default constructor
    Food(){
        id = 0;
        name = null;
        price = 0;
        quantity = 0; 
        variation = null;
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
    public abstract String getVariation(); //polymorph
}

class Burger extends Food {
    //Default constructor
    Burger(){
        super();
    }

    //Parameterised constructor
    Burger(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //Parameterised constructor
    Burger(int id, String name, double price, int quantity, String variation){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.variation = variation;
    }

    //polymorphism
    public String getVariation(){
        return name + " with " + variation;
    }

}

class Chicken extends Food {
    //Default constructor
    Chicken(){
        super();
    }

    //Parameterised constructor
    Chicken(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //Parameterised constructor
    Chicken(int id, String name, double price, int quantity, String variation){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.variation = variation;
    }

    //polymorphism
    public String getVariation(){
        return variation + " " + name;
    }
}
