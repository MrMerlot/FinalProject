package Controller;

import Model.OrderData;
import View.CookView;

public class CookController {

    OrderData orderData = new OrderData();      //new instance of OrderData
    CookView cookView;                          //instance of CookView


    public CookController(CookView view){
        this.cookView = view;                   //how controller communicates with the view
    }

    public void test(){
        orderData.setPickupOrders("Burger,Fries");
        orderData.setPickupOrders("Hotdog,Fries");
    }

    public static void main(String[] args) {

    }

}
