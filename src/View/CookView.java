package View;

import Controller.CookController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class CookView extends Application {

    private Label nextOrderLabel = new Label( "Test NO" );                                       //shows next order

    private Button toggleView = new Button( "Switch to Customer View" );        //Switches back to the Customers View

    private Button finishOrder = new Button( "Finish Current Order" );          //adds order to be picked up, updates current and next order Labels

    private Button showPickupOrders = new Button( "List all Pickup Orders");    //displays all orders to be picked up

    private Label cv = new Label( "Cook View");

    private Label currentOrder = new Label( "test CO" );

    CookController cookController;
    public CookView(CookController controller){
        cookController = controller;
        setLayout();
    }
    public void setLayout(){
        nextOrderLabel.setLayoutX(900);
        nextOrderLabel.setLayoutY(10);

        toggleView.setLayoutX(0);
        toggleView.setLayoutY(575);


        finishOrder.setLayoutX(0);
        finishOrder.setLayoutY(350);

        showPickupOrders.setLayoutX(875);
        showPickupOrders.setLayoutY(350);

        cv.setLayoutX(0);
        cv.setLayoutY(0);
        cv.setFont(new Font("Arial",40));
        cv.setStyle("-fx-border-width: 2;");
        cv.setStyle("-fx-border-color: black;");


        currentOrder.setLayoutX(50);
        currentOrder.setLayoutY(150);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.getChildren().addAll(nextOrderLabel,toggleView,finishOrder,showPickupOrders,cv,currentOrder);

        Scene scene = new Scene(pane,1000,600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);

    }
}
