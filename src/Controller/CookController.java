package Controller;

import Model.OrderData;
import View.CookView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CookController implements EventHandler<ActionEvent> {

    OrderData orderData = new OrderData();      //new instance of OrderData
    CookView cookView;                          //instance of CookView


    public CookController(CookView view) {
        this.cookView = view;                   //how controller communicates with the view
    }

    public void test() {
        orderData.setPickupOrders("Burger,Fries");
        orderData.setPickupOrders("Hotdog,Fries");


//        cookView.showPickupOrders.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
////                    for(int i = 0; i < orderData.getPickupOrders().size();i++){
////                        String temp = orderData.getPickupOrders().get(i);
////                    }
//                System.out.println("This better work");
//            }
//        });
    }

    public static void main(String[] args) {

    }

    @Override
    public void handle(ActionEvent event) {
        cookView.getPickupOrders().setOnMouseClicked(new EventHandler<MouseEvent>() {   //Event Handler for pickupOrders
            @Override
            public void handle(MouseEvent event) {
                orderData.setPickupOrders("Burger,Fries");              //next 4 lines are temp
                orderData.setPickupOrders("Hotdog,Fries");
                orderData.setPickupOrders("Hotdog,Rossie");
                orderData.setPickupOrders("fdsdf,vcow");
                ToggleGroup pickupOrdersTG = new ToggleGroup();         //creating a new ToggleGroup
                VBox vBox = new VBox();                                 //creating a new vBox
                for(int i = 0; i < orderData.getPickupOrders().size();i++){ //iterates through ArrayList(pickupOrders)
                    String temp = orderData.getPickupOrders().get(i);       //stores each index in a temp String
                    RadioButton po = new RadioButton(temp);                 //each index is then made into a RadioButton
                    po.setToggleGroup(pickupOrdersTG);                      //setting each RB to the pickup ToggleGroup
                    vBox.getChildren().add(po);                             //adding each Radio Button to the vBox
                    }

                vBox.setLayoutX(400);                                       //setting the x-axis
                vBox.setLayoutY(100);                                       //setting the y-axis
                vBox.setSpacing(10);                                        //setting the spacing between each RB
                vBox.setPadding(new Insets(10));            //setting the padding(Intset)
                cookView.setPane(vBox);                                    //sending it to the Pane in view to be shown
            }
        });

        cookView.getToggleView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {              //this is a test case, will be changed
                System.out.println("NOt hsidoaf");
            }
        });

    }
}
