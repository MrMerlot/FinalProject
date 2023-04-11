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
import sun.font.TrueTypeFont;

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

    public void clearMenu(){
        cv.getMealGroup().setVisible(false);
        cv.getDrinkGroup().setVisible(false);
        cv.getSideGroup().setVisible(false);
        cv.getIcedGroup().setVisible(false);
        cv.getHotDrinkGroup().setVisible(false);
        cv.getColdMealGroup().setVisible(false);
        cv.getHotMealGroup().setVisible(false);
    }

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
            }
        });
    }

    /**
     * Finds and returns the selected item's ID
     * @return  item ID number
     */
    private int getItemID(){
        int id = 0;

        if( cv.getIcedToggleG().getSelectedToggle() == cv.getIcedTea() ){ id = 1; }
        else if( cv.getIcedToggleG().getSelectedToggle() == cv.getWater() ){ id = 2; }

        return id;
    }
}
