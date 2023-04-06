package Controller;

import Model.Order;
import Model.OrderData;
import View.CustomerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderController {
    CustomerView cv = new CustomerView();
    OrderData orderData = new OrderData();
    public OrderController( CustomerView cv ){
        this.cv = cv;
    }

    /**
     * Creates an order object, getting the nessesary information
     */
    public void createOrder(){
        Order order = new Order(getOrderNum(),getItemID(),getQuantity(),getOrderType(),getOrderInfo(),getName());
    }
    public int getOrderNum(){
        return 1;
    }
    public ArrayList<Integer> getItemID(){
        return null;
    }
    public ArrayList<Integer> getQuantity(){
        return null;
    }
    public int getOrderType(){
        return 1;
    }
    public String getOrderInfo(){
        return " ";
    }
    public String getName(){
        return " ";
    }

}

