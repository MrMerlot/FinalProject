package Controller;
import Model.*;
import View.CustomerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class OrderController implements EventHandler<ActionEvent> {
    CustomerView cv;
    public OrderController( CustomerView cv ){
        this.cv = cv;
    }

    private String customerName;
    private String orderType;


    public String setOrderName( String name ){



        return null;
    }


    @Override
    public void handle(ActionEvent event) {

        cv.getNameButton().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if( event.getCode().equals(KeyCode.ENTER)){

                    customerName = cv.getNameButton().getText();
                    orderType = cv.getOrderType().getText();

                    //Order order = new Order()
                }
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
