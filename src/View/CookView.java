package View;

import Controller.CookController;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class CookView extends Application {
    CustomerView customerView1;
    public Scene secondScene;
    private Label nextOrderLabel = new Label( "" );                         //shows next order

    private Button toggleView = new Button( "Switch to Customer View" );        //Switches back to the Customers View

    private Button finishOrder = new Button( "Finish Current Order" );          //adds order to be picked up, updates current and next order Labels

    private CheckBox showPickupOrders = new CheckBox( "List all Pickup Orders");    //displays all orders to be picked up

    private Button backPickUP = new Button( "Back" );                           //Back Button

    private Button pickup = new Button( "Pickup Order" );                       //Pickup Order Button

    private final Label CV = new Label( "Cook View");                           //Label for what Vew you are in

    private Label currentOrder = new Label( "" );                        //Label for the current Order

    private Pane pane = new Pane();                                                 //Creating a new Pane



    CookController controller = new CookController(this);
    public CookView(CustomerView customerView){
        customerView1 = customerView;
        pane.getChildren().addAll(nextOrderLabel, toggleView, finishOrder, showPickupOrders, CV, currentOrder);
        setLayout();
        setScene();
    }
    public void setLayout(){
        nextOrderLabel.setLayoutX(470);                  //setting the x-axis
        nextOrderLabel.setLayoutY(10);                  //setting the y-axis

        toggleView.setLayoutX(0);                       //setting the x-axis
        toggleView.setLayoutY(575);                     //setting the y-axis
        toggleView.setOnAction(controller);            //sending to Cook Controller when Clicked


        finishOrder.setLayoutX(0);                     //setting the x-axis
        finishOrder.setLayoutY(350);                   //setting the y-axis
        finishOrder.setOnAction(controller);

        showPickupOrders.setLayoutX(445);               //setting the x-axis
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

    public void toggle(){
        customerView1.toggle();
    }

    public void setScene(){
        secondScene = new Scene( pane, 600,600 );
    }

    public Scene getScene(){ return secondScene; }

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
    public CheckBox getPickupOrders(){
        return showPickupOrders;
    }
    public Button getPickup(){
        return pickup;
    }

    /**
     * Allows the pane to be updated from the controller class
     * @param node node
     */
    public void setPane(Node node){
        this.pane.getChildren().add(node);
    }
    public Pane getPane(){
        return pane;
    }

    public void removeRB(Node node){
        pane.getChildren().remove(node);
    }

    /**
     * Allows the button to be updated from the controller class
     * @return Button
     */
    public Button getBackPickup(){
        return backPickUP;
    }
    public Button getFinishOrder(){return finishOrder;}

    /**
     * Sets the text for the current order
     * @param currentOrder String
     */
    public void setCurrentOrderLabel(String currentOrder){
        this.currentOrder.setText(currentOrder);
    }

    /**
     * Gets the Label for the Current Order
     * @return Label
     */
    public Label getCurrentOrder(){
        return this.currentOrder;
    }


    /**
     * Sets the text for the Next Order
     * @param nextOrder String
     */
    public void setNextOrderLabel(String nextOrder){
        this.nextOrderLabel.setText(nextOrder);
    }

    /**
     * Gets the Label for the Next Order
     * @return Label
     */
    public Label getNextOrderLabel(){
        return nextOrderLabel;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.getChildren().addAll(nextOrderLabel, toggleView, finishOrder, showPickupOrders, CV, currentOrder);



        Scene scene = new Scene(pane, 600, 600);     //instantiating scene and adding the pane & window size
        primaryStage.setScene(scene);                           //setting the scene
        primaryStage.setResizable(false);                       //making sure the user can't resize the window
        primaryStage.show();                                    //calling show

    }

    public Scene getSecondScene(){
        return secondScene;
    }


    public static void main(String[] args) {
        Application.launch(args);                           //calling launch

    }

}
