package Controller;
import Exceptions.CustomerNameException;
import Exceptions.PhoneNumberException;
import Exceptions.SubmitOrderException;
import Model.*;
import View.CustomerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import sun.font.TrueTypeFont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;

public class OrderController implements EventHandler<ActionEvent> {
    private CustomerView cv;

    private OrderData orderData = new OrderData();
    private String customerName;
    private int orderType;
    private int orderNumber;
    private boolean flip = false;
    private OrderDataController orderDataController = new OrderDataController(this);
    private ArrayList<Order> ordersArrayList = new ArrayList<>();
    private HashTableID hashTableID = new HashTableID();


    /**
     * Constructor that connects to CustomerView
     * @param cv
     */
    public OrderController( CustomerView cv ){ this.cv = cv; }

    /**
     * Sets the visibility of menu to false.
     */
    private void clearMenu(){
        cv.getMealGroup().setVisible(false);
        cv.getDrinkGroup().setVisible(false);
        cv.getSideGroup().setVisible(false);
        cv.getIcedGroup().setVisible(false);
        cv.getHotDrinkGroup().setVisible(false);
        cv.getColdMealGroup().setVisible(false);
        cv.getHotMealGroup().setVisible(false);
    }

    /**
     * Sets phone inquiries to false.
     */
    private void clearPhone(){
        cv.getGetPhoneNumber().setVisible(false);
        cv.getEnterPhone().setVisible(false);
    }

    /**
     * Checks if the inputted String is an accepted phone number or not
     * @param num
     * @return boolean
     */
    private boolean isPhoneNumber(String num){
        if(num.equals(""))
            return false;
        for(int i=0; i < num.length(); i++){
            if(num.charAt(i) < 48 || num.charAt(i) > 57){
                return false;
            }
        }
        return true;
    }


    /**
     * Takes in an order number(int) and returns its corresponding order object(Object)
     *
     * @param orderNumber
     * @return order
     */
    public Order orderNumToOrder(int orderNumber){
        for(int i = 0; i < ordersArrayList.size(); i++){
            if(ordersArrayList.get(i).getOrderNumber() == orderNumber)
                return ordersArrayList.get(i);
        }

        //instead of returning null, throw an exception                     //EXCEPTION
        return null;
    }

    /**
     * Handles the buttons in CustomerView
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {

        cv.getCancel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orderDataController.cancelOrder(orderNumToOrder(Integer.parseInt(cv.getCanceledOrder())));
                cv.setCancelField("");
            }
        });

        /**
         * When submit is hit, a new order is created
         */
        cv.getSubmit().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try{
                    if(cv.getNameButton().getText().equals("")){
                        throw new CustomerNameException();
                    }
                    if(getType() == 3 && !isPhoneNumber(cv.getGetPhoneNumber().getText()))
                        throw new PhoneNumberException();
                    if(cv.getItemID().isEmpty())
                        throw new SubmitOrderException();

                    customerName = cv.getNameButton().getText();
                    orderType = getType( );
                    orderNumber = Integer.parseInt( cv.getOrderNumber().getText() );
                    String phone = cv.getGetPhoneNumber().getText();
                    Order order;
                    cv.addOrderNumber();

                    if( orderType == 4 ) order = new DoorDash( customerName, orderNumber);
                    else if( orderType == 1 ) order = new DriveThrough( customerName, orderNumber);
                    else if( orderType == 2 ) order = new Onsite( customerName, orderNumber);
                    else order = new Phone( customerName, orderNumber, phone);

                    String items = "";//test
                    for( int i = 0; i < cv.getItemID().size(); i++ ){
                        order.addItem( cv.getItemID().get(i), cv.getItemQuantity().get(i) );
                        items +=  "\n"+ cv.getItemID().get(i) + " " + cv.getItemQuantity().get(i);//test
                    }
                    System.out.println("ORDER#" + orderNumber + customerName +" "+orderType+ " "+items);//test

                    if( orderType == 4 ) {
                        cv.getPriceLabel().setText( "$" + (1.05 * getPrice(cv.getItemID(), cv.getItemQuantity()) ));
                    }
                    else{
                        cv.getPriceLabel().setText( "$" + getPrice(cv.getItemID(), cv.getItemQuantity()));
                    }
                    cv.getItemID().clear();
                    cv.getItemQuantity().clear();
                    orderData.addOrder( order );
                    ordersArrayList.add(order);
                    orderDataController.checkQueue();
                }
                catch(CustomerNameException e){}
                catch (PhoneNumberException e){}
                catch(SubmitOrderException e){}

            }
        });

        /**
         * The following determines what menu layer is visible.
         */
        cv.getDoneButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { clearMenu(); }
        });
        cv.getDrinkButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getDrinkGroup().setVisible(true);
            }});
        cv.getMealButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getMealGroup().setVisible(true);
            }});
        cv.getSidesButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getSideGroup().setVisible(true);
            }});
        cv.getColdDrink().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getIcedGroup().setVisible(true);
                cv.getDrinkGroup().setVisible(true);
            }});
        cv.getHotDrink().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getHotDrinkGroup().setVisible(true);
                cv.getDrinkGroup().setVisible(true);
            }});
        cv.getHotMeal().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getHotMealGroup().setVisible(true);
                cv.getMealGroup().setVisible(true);
            }});
        cv.getColdMeal().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearMenu();
                cv.getColdMealGroup().setVisible(true);
                cv.getMealGroup().setVisible(true);
            }});

        /**
         * This adds the correct item ID and quantity to the arraylist
         */
        cv.getAddToOrder().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cv.addItemQuantity( (int) cv.getQuantitySlider().getValue() );
                cv.addItemID( getItemID() );
            }
        });

        cv.getToggleView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cv.toggle();

            }
        });

        cv.getPhoneRadio().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                cv.getGetPhoneNumber().setVisible(true);
                cv.getEnterPhone().setVisible(true);
            }});
        cv.getOnsiteRadio().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { clearPhone(); }});
        cv.getDoorDRadio().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { clearPhone(); }});
        cv.getDriveRadio().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { clearPhone(); }});
    }

    /**
     * Finds and returns the selected item's ID
     * @return  item ID number
     */
    private int getItemID(){
        int id = 0;

        if( cv.getIcedTea().isSelected() && cv.getColdDrink().isSelected()
                && cv.getDrinkButton().isSelected() ){
            id = 1;
        }
        else if( cv.getWater().isSelected() &&  cv.getColdDrink().isSelected()
                && cv.getDrinkButton().isSelected()){
            id = 2;
        }
        else if( cv.getHotCoffee().isSelected() && cv.getHotDrink().isSelected()
                && cv.getDrinkButton().isSelected() ){
            id = 3;
        }
        else if( cv.getHotChoco().isSelected() && cv.getHotDrink().isSelected()
                && cv.getDrinkButton().isSelected() ){
            id = 4;
        }
        else if( cv.getIcedNoodles().isSelected() && cv.getColdMeal().isSelected()
                && cv.getMealButton().isSelected() ){
            id = 5;
        }
        else if( cv.getWatermelonSalad().isSelected() && cv.getColdMeal().isSelected()
                && cv.getMealButton().isSelected() ){
            id = 6;
        }
        else if( cv.getPineapplePizza().isSelected() && cv.getHotMeal().isSelected()
                && cv.getMealButton().isSelected() ){
            id = 7;
        }
        else if( cv.getCheeseBurger().isSelected() && cv.getHotMeal().isSelected()
                && cv.getMealButton().isSelected() ){
            id = 8;
        }
        else if( cv.getFries().isSelected() && cv.getSidesButton().isSelected() ){
            id = 9;
        }
        else if( cv.getFriedOreos().isSelected() && cv.getSidesButton().isSelected() ){
            id = 10;
        }
        else if( cv.getPickles().isSelected() && cv.getSidesButton().isSelected() ){
            id = 11;
        }

        return id;
    }

    public Order getOrder(int i){
        return ordersArrayList.get(i);
    }

    public int getOrdersArrayListLength(){
        return ordersArrayList.size();
    }

    private int getType(){
        int type = 0;

        if( cv.getDriveRadio().isSelected() ) type = 1;
        else if( cv.getOnsiteRadio().isSelected() ) type = 2;
        else if( cv.getPhoneRadio().isSelected() ) type = 3;
        else if( cv.getDoorDRadio().isSelected() ) type = 4;

        return type;
    }

    private double getPrice( ArrayList<Integer> item, ArrayList<Integer> quantity ){

        double price = 0;

        for( int i = 0; i < item.size(); i++ ){

            int ID = item.get(i);
            price += quantity.get(i) * hashTableID.getItemPrice(ID);
        }

        return price;
    }

    public void closeFileAction(){
            try {
                FileWriterController fileWriterController = new FileWriterController();
                for(int i=0;i<getOrdersArrayListLength();i++){
                    fileWriterController.writeToFile(getOrder(i), fileWriterController);
                }//commit
                fileWriterController.close();
            } catch (IOException e) {
                System.out.println("ERROR");
                cv.stage.close();
                throw new RuntimeException(e);
            }
    }
}
