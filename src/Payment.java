/*
*  Name         : Qhairul Heedayah Binti Tamsor (A20EC0134)
*  Section      : 12
*  Instructor   : Dr. Arafat Mohammed Rashad Al-dhaqm
*  Last Modified: 2 July 2022, 9:32 p.m.
*
*  Brief Description: 
*  Payment class requires Order Arraylist to function properly. It then can
*  calculate total price and balance based on the input arraylist.
*/


import java.util.*;

public class Payment {
    double totalPaid;
    double totalPrice;
    ArrayList<Order> order = new ArrayList<Order>();

    Payment(ArrayList<Order> order){
        this.order = order;
        totalPaid = 0.0;
        totalPrice = 0.0;
    }

    public void addTotalPayment(double addPayment){
        totalPaid += addPayment;
    }

    public double balance(){
        return totalPrice - totalPaid;
    }

    public void updateTotalPrice(){
        totalPrice = 0; // need to reset, so the previous order list will not combine with current loop
        for(int i = 0; i < order.size(); i++){
            totalPrice += order.get(i).getPrice()*order.get(i).getQuantity();
        }
    }

    public boolean checkIfInteger(String str){
        boolean check = false;
        try {
            int x = Integer.parseInt(str);
            check = true;
        } catch (Exception e){
            //Do nothing
        }
        return check;
    }

    public boolean checkIfDouble(String str){
        boolean check = false;
        try {
            double x = Double.parseDouble(str);
            check = true;
        } catch (Exception e){
            //Do nothing
        }
        return check;
    }

    //Mutator
    public void setTotalPaid(double totalPaid){this.totalPaid = totalPaid;}
    public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}

    //Accessor
    public double getTotalPaid(){return totalPaid;}
    public double getTotalPrice(){return totalPrice;}
}
