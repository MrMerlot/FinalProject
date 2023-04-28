package Controller;

import Exceptions.FinishOrderException;
import Exceptions.PickupOrderException;
import Model.HashTableID;
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

import java.io.IOException;

public class CookController implements EventHandler<ActionEvent> {

    OrderDataController orderDataController = new OrderDataController(this);
    OrderData orderData = new OrderData();      //new instance of OrderData
    CookView cookView;                          //instance of CookView
    ToggleGroup pickupOrdersTG = new ToggleGroup(); //New Toggle Group

    VBox tempVbox = new VBox();                 //New vBox
    FileWriterController fileWriterController = new FileWriterController();

    HashTableID hashTableID = new HashTableID();


    public CookController(CookView view) {
        this.cookView = view;                   //how controller communicates with the view
        //test();
        resetVbox();
        setOrders();
    }



    /**
     * resets the Vbox
     */
    public void resetVbox(){
        if (!cookView.getPickupOrders().isSelected()){
            cookView.getPane().getChildren().remove(tempVbox);      //removing vBox from the pane
            tempVbox.getChildren().clear();                         //resetting the vBox
        }
    }


    /**
     * sets the current and next order labels
     */
    public void setOrders(){
        cookView.setCurrentOrderLabel(orderData.getCurrentOrder());
        cookView.setNextOrderLabel(orderData.getNextOrder());
    }

    /**
     * gets the current order
     * @return String
     */
    public String getCurrentOrder(){
        return orderData.getCurrentOrder();
    }

    /**
     * gets the next order
     * @return String
     */
    public String getNextOrder(){
        return orderData.getNextOrder();
    }

    @Override
    public void handle(ActionEvent event) {


        if (cookView.getPickupOrders().isSelected()){

            VBox vBox = new VBox();                                //creating a new vBox
            cookView.getPane().getChildren().remove(tempVbox);    //in case user doesn't use back button or pickup order
            HBox hBox = new HBox();                                 //creating a new hBox
            for(int j=0;j< fileWriterController.getOrdersArrayListLength();j++){
                if(FileWriterController.fileOrderArrayList.get(j).getIsComplete()==true){
                    String temp = FileWriterController.fileOrderArrayList.get(j).getName();
                    for(int x=0;x<FileWriterController.fileOrderArrayList.get(j).getItemID().size();x++) {
                       temp+="\n"+FileWriterController.fileOrderArrayList.get(j).getItemQuantity(x) +" "+
                                hashTableID.getItemIDName(FileWriterController.fileOrderArrayList.get(j).getItemID(x));
                    }
                    RadioButton po = new RadioButton(temp);
                    po.setToggleGroup(pickupOrdersTG);
                    vBox.getChildren().add(po);
                }
            }
            /*for(int i = 0; i < orderData.getPickupOrders().size();i++){ //iterates through ArrayList(pickupOrders)
                String temp = orderData.getPickupOrders().get(i);       //stores each index in a temp String
                RadioButton po = new RadioButton(temp);                 //each index is then made into a RadioButton
                po.setToggleGroup(pickupOrdersTG);                      //setting each RB to the pickup ToggleGroup
                vBox.getChildren().add(po);                             //adding each Radio Button to the vBox
            }*/
            vBox.setLayoutX(225);                                       //setting the x-axis
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
                try{     //exception handling for PickupOrder button
                    //checking to see if pickupOrder Arraylist is empty or if the user didn't select an order
                    if(pickupOrdersTG.getSelectedToggle() == null) {
                        throw new PickupOrderException();   //throwing the exception
                    }
                    RadioButton temp = (RadioButton) pickupOrdersTG.getSelectedToggle(); //setting the selected RP to a temp
                    String tempS = temp.getText();                            //getting the text(Order) from the selected RB
                    orderData.getPickupOrders().remove(tempS);//removing the order from Pick up orders
                    fileWriterController.removeOrder(tempS.substring(0,tempS.indexOf("\n")));
                    cookView.getPickupOrders().setSelected(false);            //unselecting the checkbox
                    resetVbox();                                              //resetting everything in the CheckBox Handler
                    pickupOrdersTG.selectToggle(null);
                }
                catch(PickupOrderException e){}
            }
        });

        cookView.getToggleView().setOnMouseClicked(event1 -> {
            cookView.toggle();
        });


        /**
         * Handler for the Finish Order Button
         */
        cookView.getFinishOrder().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if(cookView.getCurrentOrder().getText().equals("")){
                        throw new FinishOrderException();
                    }
                    if (orderData.getCurrentOrderObject().getOrderType() == 1 || orderData.getCurrentOrderObject().getOrderType() == 2) {
                        fileWriterController.removeOrder(orderData.getCurrentOrder().substring(0,orderData.getCurrentOrder().indexOf("\n")));
                    }
                    if(cookView.getNextOrderLabel().getText().equals("") && !orderDataController.isEmpty()){
                        orderDataController.setNextOrder();
                    }
                    else if(cookView.getNextOrderLabel().getText().equals("") && orderDataController.isEmpty()){
                        orderData.getOrderList().remove(orderData.getNextOrderObject());
                        if(orderData.getCurrentOrderObject().getOrderType() == 3 || orderData.getCurrentOrderObject().getOrderType() == 4){
                            orderData.setPickupOrders(orderData.getCurrentOrder());//Adds the Finished Order to Pickup
                            orderData.getCurrentOrderObject().setComplete("true");
                        }
                        cookView.setCurrentOrderLabel("");            //Sets the Label to the new Order
                        orderData.setCurrentOrder("");
                        cookView.getPane().getChildren().removeAll(cookView.getCurrentOrder(), cookView.getNextOrderLabel());//removes the old Label from Pane
                        cookView.getPane().getChildren().addAll(cookView.getCurrentOrder(), cookView.getNextOrderLabel());   //Adds new Label to the pane
                    }
                    else {
                        if(orderData.getCurrentOrderObject().getOrderType() == 3 || orderData.getCurrentOrderObject().getOrderType() == 4){
                            orderData.setPickupOrders(orderData.getCurrentOrder());//Adds the Finished Order to Pickup
                        }
                        orderData.getOrderList().remove(orderData.getNextOrderObject());
                        orderData.getCurrentOrderObject().setComplete("true");
                        orderDataController.setCurrentOrder();       //sets the current and next order V
                        cookView.setCurrentOrderLabel(orderData.getCurrentOrder());            //Sets the Label to the new Order
                        cookView.setNextOrderLabel(orderData.getNextOrder());               //Sets the Label for the next order
                        cookView.getPane().getChildren().removeAll(cookView.getCurrentOrder(), cookView.getNextOrderLabel());//removes the old Label from Pane
                        cookView.getPane().getChildren().addAll(cookView.getCurrentOrder(), cookView.getNextOrderLabel());   //Adds new Label to the pane
                    }
                }
                catch (FinishOrderException e){}
            }

        });

    }//end of handler
}//end of Cook Controller
