package Controller;
import Model.*;
import View.CustomerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class OrderController implements EventHandler<ActionEvent> {
    private CustomerView cv;

    private OrderData orderData = new OrderData();
    private String customerName;
    private int orderType;
    private int orderNumber;
    private boolean flip = false;

    /**
     * Constructor that connects to CustomerView
     * @param cv
     */
    public OrderController( CustomerView cv ){ this.cv = cv; }

    /**
     * Handles the buttons in CustomerView
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {

        /**
         * When submit is hit, a new order is created
         */
        cv.getSubmit().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                customerName = cv.getNameButton().getText();
                orderType = Integer.parseInt( cv.getOrderType().getText() );
                orderNumber = Integer.parseInt( cv.getOrderNumber().getText() );
                Order order;
                cv.addOrderNumber();

                if( orderType == 1 ) order = new DoorDash( customerName, orderNumber, orderType);
                else if( orderType == 2 ) order = new DriveThrough( customerName, orderNumber, orderType );
                else if( orderType == 3 ) order = new Onsite( customerName, orderNumber, orderType);
                else order = new Phone( customerName, orderNumber, orderType);

                orderData.addOrder( order );
            }
        });

        /**
         * Toggles the view
         */
        cv.getToggleButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

    /**
     * Creates an order object with the nessesary information
     *
     *
     * @throws IOException
     */
//    public void createOrder() throws IOException {
//        if(getOrderType() == 1) {
//            DoorDash order = new DoorDash(getName(), getOrderNum());
//        }
//        else if(getOrderType() == 2){
//            DriveThrough order = new DriveThrough(getName(), getOrderNum());
//        }
//        else if(getOrderType() == 3){
//            Onsite order = new Onsite(getName(), getOrderNum());
//        }
//        else{
//            Phone order = new Phone(getName(), getOrderNum());
//        }
//    }
//
//
//    public int getOrderNum(){
//        return cv.orderNum;
//    }
//    public ArrayList<Integer> getItemID(){
//        return cv.itemID;
//    }
//    public ArrayList<Integer> getQuantity(){
//        return cv.quantity;
//    }
//    public int getOrderType(){
//        return cv.orderType;
//    }
//    public String getOrderInfo(){
//        return cv.orderInfo;
//    }
//    public String getName(){
//        return cv.name;
//    }

}
