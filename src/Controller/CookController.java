package Controller;

import Model.Order;
import Model.OrderData;
import View.CookView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CookController implements EventHandler<ActionEvent> {

    OrderDataController orderDataController = new OrderDataController();
    OrderData orderData = new OrderData();      //new instance of OrderData
    CookView cookView;                          //instance of CookView
    ToggleGroup pickupOrdersTG = new ToggleGroup(); //New Toggle Group

    VBox tempVbox = new VBox();                 //New vBox


    public CookController(CookView view) {
        this.cookView = view;                   //how controller communicates with the view
        test();
        resetVbox();
        setOrders();
    }

    public void test() {
        orderData.setPickupOrders("dd");
        orderData.setPickupOrders("fsddf");
        orderData.setPickupOrders("sdfsdfsdf");                 //this will be removed soon
        orderData.setPickupOrders("falskdfj;alk");

        orderData.setCurrentOrder("2 HotDogs 3 Burgers Hot Ice 7 Pizza 8 hot tea");
        orderData.setNextOrder("9HotDogs 4Burgers Hot Ice 2 pizza 12 ham");

        Order test = new Order("Steve",2,2);
        test.addItem(2,6);
        Order test2 = new Order("Billy",4,3);
        test2.addItem(5,7);
        Order test4 = new Order("Tom",4,3);
        test4.addItem(8,2);
        Order test3 = new Order("Miles",2,2);
        test3.addItem(6,4);

        orderData.setDriveThroughQueue(test);
        orderData.setOnSiteQueue(test2);
        orderData.setDriveThroughQueue(test3);
        orderData.setOnSiteQueue(test4);
    }

    public static void main(String[] args) {

    }


    /**
     * resets the V
     */
    public void resetVbox(){
        if (!cookView.getPickupOrders().isSelected()){
            cookView.getPane().getChildren().remove(tempVbox);      //removing vBox from the pane
            tempVbox.getChildren().clear();                         //resetting the vBox
        }
    }

    public void setOrders(){
        cookView.setCurrentOrderLabel(orderData.getCurrentOrder());
        cookView.setNextOrderLabel(orderData.getNextOrder());
    }

    @Override
    public void handle(ActionEvent event) {


        if (cookView.getPickupOrders().isSelected()){

            VBox vBox = new VBox();                                //creating a new vBox
            cookView.getPane().getChildren().remove(tempVbox);    //in case user doesn't use back button or pickup order
            HBox hBox = new HBox();                                 //creating a new hBox
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
            hBox.getChildren().addAll(cookView.getBackPickup(),cookView.getPickup());//adding to hBox
            vBox.getChildren().add(hBox);                              //adding the HBox to vBox
            vBox.visibleProperty().bind(cookView.getPickupOrders().selectedProperty());//sets when Vbox is Visible
            tempVbox = vBox;                                                //setting the vbox to a temporary Vbox
            cookView.setPane(tempVbox);                                    //sending it to the Pane in view to be shown


        }




        /**
         * Handler for the back button when List pickup orders is selected
         */
        cookView.getBackPickup().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cookView.getPickupOrders().setSelected(false);           ////unselecting the checkbox
                resetVbox();                                             //resetting everything in the CheckBox Handler

            }
        });


        /**
         * Handler for picking up a completed order
         */
        cookView.getPickup().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RadioButton temp = (RadioButton) pickupOrdersTG.getSelectedToggle(); //setting the selected RP to a temp
                String tempS = temp.getText();                            //getting the text(Order) from the selected RB
                orderData.getPickupOrders().remove(tempS);                //removing the order from Pick up orders
                cookView.getPickupOrders().setSelected(false);            //unselecting the checkbox
                resetVbox();                                              //resetting everything in the CheckBox Handler
            }
        });

        cookView.getToggleView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {              //this is a test case, will be changed
                System.out.println("NOt hsidoaf");
            }
        });


        /**
         * Handler for the Finish Order Button
         */
        cookView.getFinishOrder().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orderData.setPickupOrders(orderData.getCurrentOrder());             //Adds the Finished Order to Pickup
//                orderData.setCurrentOrder(orderData.getNextOrder());                //Sets the CurrentOrder to NextOrder
//                cookView.setCurrentOrderLabel(orderData.getNextOrder());            //Sets the Label to the new Order
                orderDataController.setCurrentOrder();
                cookView.setCurrentOrderLabel(orderData.getCurrentOrder());            //Sets the Label to the new Order
                cookView.setNextOrderLabel(orderData.getNextOrder());
                cookView.getPane().getChildren().removeAll(cookView.getCurrentOrder(),cookView.getNextOrderLabel());//removes the old Label from Pane
                cookView.getPane().getChildren().addAll(cookView.getCurrentOrder(), cookView.getNextOrderLabel());   //Adds new Label to the pane

            }
        });

    }//end of handler
}//end of Cook Controller
