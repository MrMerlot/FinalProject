package View;

import Controller.OrderController;

public class CustomerView {

    public OrderController orderController = new OrderController(this);

    public static void main(String[] args) {

        CustomerView cv = new CustomerView();

        cv.orderController.setBLL("hello");
        System.out.print( cv.orderController.getText() );
    }
}
