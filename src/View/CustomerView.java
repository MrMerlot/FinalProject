package View;

import Controller.OrderController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CustomerView extends Application {

    private Group group;
    private Label customerLabel = new Label("Customer");
    private Label orderLabel = new Label("Order # ");
    private Label orderNumberLabel = new Label(""+1);
    private Label promptLabel = new Label("Order Type? \n\nName?");
    private Label cancelLabel = new Label("Cancel Order #");
    private TextField cancelField = new TextField("__");
    private Button toggleView = new Button("Switch to Cook View");
    private Button submitButton = new Button("Submit Order");
    private TextField nameField = new TextField("Type name");
    private TextField typeField = new TextField("Enter type");
    private Label priceLabel = new Label("Price");
    private Label menuLabel = new Label("Menu:");

    //  Connects CustomerView to OrderController
    private OrderController orderController = new OrderController(this);


    @Override
    public void start(Stage primaryStage) throws Exception {

        setPositions();

        submitButton.setOnAction( orderController );
        nameField.setOnAction( orderController );

        group = returnNodes();

        Scene scene = new Scene(group,1000,600);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        System.out.println();
        primaryStage.setTitle("CustomerView");
        primaryStage.show();
    }

    /**
     * Sets the positions of buttons or labels
     */
    private void setPositions(){

        orderLabel.setLayoutX(900);
        orderNumberLabel.setLayoutX(950);
        promptLabel.setLayoutY(100);
        cancelLabel.setLayoutY(500);
        cancelLabel.setLayoutX(900);
        priceLabel.setLayoutX(470);
        priceLabel.setLayoutY(570);
        cancelField.setLayoutX(980);
        cancelField.setLayoutY(500);
        cancelField.setMaxWidth(20);
        typeField.setLayoutX(100);
        typeField.setLayoutY(100);
        nameField.setLayoutX(100);
        nameField.setLayoutY(135);
        toggleView.setLayoutY(570);
        submitButton.setLayoutX(900);
        submitButton.setLayoutY(570);
        menuLabel.setLayoutY(200);
        menuLabel.setLayoutX(40);
    }

    /**
     * Adds all the nodes to a group.
     *
     * @return  The group containing the nodes.
     */
    private Group returnNodes(){
        Group g = new Group();
        g.getChildren().addAll(customerLabel, orderLabel, promptLabel,
                cancelField, toggleView, submitButton, cancelLabel, nameField,
                typeField, priceLabel, menuLabel, orderNumberLabel);

        return g;
    }

    /**
     * Gets the submit button.
     *
     * @return  Submit button
     */
    public Button getSubmit(){
        return submitButton;
    }

    /**
     * Gets the nameField
     * @return  nameField
     */
    public TextField getNameButton(){
        return nameField;
    }

    /**
     * Gets typeField
     * @return typeField
     */
    public TextField getOrderType(){
        return typeField;
    }

    /**
     * Gets the order number
     * @return  the order number
     */
    public Label getOrderNumber(){
        return orderNumberLabel;
    }

    /**
     * Increments the order number
     */
    public void addOrderNumber(){
        int num = Integer.parseInt( orderNumberLabel.getText() ) + 1;
        orderNumberLabel.setText( String.valueOf( num ) );
    }
    public static void main(String[] args) throws Exception {
        Application.launch();
        //CookView cookView = new CookView();
        //cookView.begin();
    }

}
