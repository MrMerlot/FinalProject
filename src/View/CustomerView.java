package View;

import Controller.OrderController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class CustomerView extends Application {

    private Group group;
    private Label customerLabel = new Label("Customer");
    private Label orderLabel = new Label("Order # 1");
    private Label promptLabel = new Label("Order Type? \n\nName?");
    private Label cancelLabel = new Label("Cancel Order #");
    private TextField cancelField = new TextField("__");
    private Button toggleView = new Button("Switch to Cook View");
    private Button submitButton = new Button("Submit Order");
    private TextField nameField = new TextField("Type name");
    private TextField typeField = new TextField("Enter type");
    private Label priceLabel = new Label("Price");
    private Label menuLabel = new Label("Menu:");


    @Override
    public void start(Stage primaryStage) throws Exception {

        setPositions();

        group = returnNodes();

        Scene scene = new Scene(group,1000,600);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CustomerView");
        primaryStage.show();
    }

    public class ButtonCaller implements EventHandler{
        @Override
        public void handle(Event event) {

            if( event.equals(submitButton) );
        }
    }

    /**
     * Sets the positions of buttons or labels
     */
    private void setPositions(){

        orderLabel.setLayoutX(900);
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
                typeField, priceLabel, menuLabel);

        return g;
    }


    public static void main(String[] args) {

        Application.launch();
    }

}
