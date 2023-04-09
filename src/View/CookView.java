package View;

import Controller.CookController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class CookView extends Application {

    private Label nextOrderLabel = new Label( "Test NO" );                                       //shows next order

    private Button toggleView = new Button( "Switch to Customer View" );        //Switches back to the Customers View

    private Button finishOrder = new Button( "Finish Current Order" );          //adds order to be picked up, updates current and next order Labels

    private Button showPickupOrders = new Button( "List all Pickup Orders");    //displays all orders to be picked up

    private final Label CV = new Label( "Cook View");

    private Label currentOrder = new Label( "test CO" );

    private Pane pane = new Pane();


    CookController controller = new CookController(this);
    public CookView(){
        setLayout();
    }
    public void setLayout(){
        nextOrderLabel.setLayoutX(900);                  //setting the x-axis
        nextOrderLabel.setLayoutY(10);                  //setting the y-axis

        toggleView.setLayoutX(0);                       //setting the x-axis
        toggleView.setLayoutY(575);                     //setting the y-axis
        toggleView.setOnAction(controller);            //sending to Cook Controller when Clicked


        finishOrder.setLayoutX(0);                     //setting the x-axis
        finishOrder.setLayoutY(350);                   //setting the y-axis

        showPickupOrders.setLayoutX(875);               //setting the x-axis
        showPickupOrders.setLayoutY(350);               //setting the y-axis
        showPickupOrders.setOnAction(controller);       //sending to Cook Controller when Clicked


        CV.setLayoutX(0);                            //setting the x-axis
        CV.setLayoutY(0);                            //setting the y-axis
        CV.setFont(new Font("Arial",40));  //using CSS sets the font and size of the Label
        CV.setStyle("-fx-border-width: 2;");
        CV.setStyle("-fx-border-color: black;");


        currentOrder.setLayoutX(50);                //setting the x-axis
        currentOrder.setLayoutY(150);               //setting the y-axis

    }


    /**
     * Allows the Cool Controller to access toggleView
     * @return Button
     */
    public Button getToggleView(){
        return toggleView;
    }

    /**
     *Allows the Cool Controller to access showPickupOrders
     * @return Button
     */
    public Button getPickupOrders(){
        return showPickupOrders;
    }

    /**
     * Allows the pane to be updated from the controller class
     * @param node node
     */
    public void setPane(Node node){
        this.pane.getChildren().add(node);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.getChildren().addAll(nextOrderLabel,toggleView,finishOrder,showPickupOrders,CV,currentOrder);

        Scene scene = new Scene(pane,1000,600);     //instantiating scene and adding the pane & window size
        primaryStage.setScene(scene);                           //setting the scene
        primaryStage.setResizable(false);                       //making sure the user can't resize the window
        primaryStage.show();                                    //calling show
    }


    public static void main(String[] args) {
        Application.launch(args);                           //calling launch

    }

}
