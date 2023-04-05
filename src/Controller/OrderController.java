package Controller;

import Model.OrderData;
import View.CustomerView;

public class OrderController {

    CustomerView cv = new CustomerView();
    BLL bll = new BLL();

    public static class BLL{

        OrderData orderData = new OrderData();

        //public String getText(){

          //  return orderData.text;
        //}
        //public void setText( String text ){

          //  orderData.text = text;
        //}
    }

    public void setBLL( String text ){

        this.bll.orderData.text = text;
    }

    public String getText(){

        return bll.orderData.getText();
    }

    public OrderController( CustomerView cv ){

        this.cv = cv;
    }
}

