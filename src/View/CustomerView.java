package View;

import Controller.FileWriterController;
import Controller.OrderController;
import Controller.OrderDataController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerView extends Application {
    public boolean toggle = true;
    public Scene firstScene;
    public Stage stage = new Stage();
    private Label customerLabel = new Label("Customer");
    private Label orderLabel = new Label("Order # ");
    private Label orderNumberLabel = new Label(""+1);
    private Label promptLabel = new Label("Enter Order Type: \n\n\n\n\nEnter Name:");
    private TextField cancelField = new TextField("");
    private Button toggleView = new Button("Switch to Cook View");
    private Button submitButton = new Button("Submit Order");
    private Button cancelButton = new Button(" ");
    private TextField nameField = new TextField();
    private Label priceLabel = new Label("");
    private Label menuLabel = new Label("Menu:");
    private CookView cookView;
    private Scene scene;
    private Group group, drinkGroup, mealGroup, sideGroup, icedGroup,
            hotDrinkGroup, coldMealGroup, hotMealGroup;
    private RadioButton hotDrink, coldDrink, hotMeal, coldMeal, fries,
            pickles, friedOreos, drinks, meals, sides, done, icedTea, water,
            hotCoffee, hotChoco, cheeseBurger, pineapplePizza, icedNoodles, watermelonSalad;
    private ToggleGroup menuToggleG1, drinkToggleG, mealToggleG, sidesToggleG,
            icedToggleG, hotDrinkToggleG, hotMealToggle, coldMealToggle, orderTypeToggle;

    private Slider quantitySlider = new Slider();
    private Button addToOrder = new Button("Add selected item(s) to order");
    private ArrayList<Integer> itemID = new ArrayList<>();
    private ArrayList<Integer> itemQuantity = new ArrayList<>();
    private RadioButton doorDRadio, driveRadio, onsiteRadio, phoneRadio;
    private Label enterPhone = new Label("Enter Number: ");
    private TextField getPhoneNumber = new TextField();

    //  Connects CustomerView to OrderController
    private OrderController orderController = new OrderController(this);

    public CustomerView() {
        cookView = new CookView(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        setMenu();
        setPositions();
        setActions();
        setSlider();
        setStyle();

        group = addNodes();
        setScene();

        stage.setResizable(false);
        toggle();
        stage.setTitle("CustomerView");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                orderController.closeFileAction();
            }
        });
    }

    private void setStyle(){

        customerLabel.setFont(new Font("Arial",40));
        customerLabel.setStyle("-fx-border-width: 2;");
        customerLabel.setStyle("-fx-border-color: black;");

        cancelField.setMinWidth(50);

        orderLabel.setFont(new Font("Arial", 20));
        orderNumberLabel.setFont(new Font("Arial", 20));

        getPhoneNumber.setVisible(false);
        enterPhone.setVisible(false);
    }

    /**
     * Sets the positions of buttons or labelsss
     */
    private void setPositions(){

        orderLabel.setLayoutX(480);
        orderNumberLabel.setLayoutX(550);
        promptLabel.setLayoutY(60);
        cancelButton.setLayoutY(495);
        cancelButton.setLayoutX(460);
        priceLabel.setLayoutX(150);
        cancelButton.setLayoutX(450);
        priceLabel.setLayoutX(130);
        priceLabel.setLayoutY(365);
        cancelField.setLayoutX(549);
        cancelField.setLayoutY(495);
        cancelField.setMaxWidth(20);
        nameField.setLayoutX(100);
        nameField.setLayoutY(140);
        toggleView.setLayoutY(575);
        submitButton.setLayoutX(10);
        submitButton.setLayoutY(360);
        addToOrder.setLayoutY(310);
        addToOrder.setLayoutX(160);
        quantitySlider.setLayoutY(315);
        quantitySlider.setLayoutX(10);
        enterPhone.setLayoutY(170);
        enterPhone.setLayoutX(0);
        getPhoneNumber.setLayoutY(170);
        getPhoneNumber.setLayoutX(100);

        menuLabel.setLayoutY(210);
        menuLabel.setLayoutX(40);
        drinks.setLayoutY(230);
        meals.setLayoutY(250);
        sides.setLayoutY(270);
        done.setLayoutY(290);

        coldDrink.setLayoutY(230);
        hotDrink.setLayoutY(250);
        coldDrink.setLayoutX(100);
        hotDrink.setLayoutX(100);

        coldMeal.setLayoutY(230);
        hotMeal.setLayoutY(250);
        coldMeal.setLayoutX(100);
        hotMeal.setLayoutX(100);

        fries.setLayoutY(230);
        friedOreos.setLayoutY(250);
        pickles.setLayoutY(270);
        fries.setLayoutX(100);
        friedOreos.setLayoutX(100);
        pickles.setLayoutX(100);

        icedTea.setLayoutY(230);
        water.setLayoutY(250);
        icedTea.setLayoutX(200);
        water.setLayoutX(200);
        hotCoffee.setLayoutY(230);
        hotChoco.setLayoutY(250);
        hotCoffee.setLayoutX(200);
        hotChoco.setLayoutX(200);

        pineapplePizza.setLayoutY(230);
        cheeseBurger.setLayoutY(250);
        icedNoodles.setLayoutY(230);
        watermelonSalad.setLayoutY(250);
        pineapplePizza.setLayoutX(200);
        cheeseBurger.setLayoutX(200);
        watermelonSalad.setLayoutX(200);
        icedNoodles.setLayoutX(200);

        onsiteRadio.setLayoutY(60);
        onsiteRadio.setLayoutX(100);
        phoneRadio.setLayoutY(80);
        phoneRadio.setLayoutX(100);
        doorDRadio.setLayoutY(100);
        doorDRadio.setLayoutX(100);
        driveRadio.setLayoutX(100);
        driveRadio.setLayoutY(120);
    }



    /**
     * Adds all the nodes to a group.
     *
     * @return  The group containing the nodes.
     */
    private Group addNodes(){
        Group g = new Group();
        g.getChildren().addAll(customerLabel, orderLabel, promptLabel,
                cancelField, toggleView, submitButton, nameField, priceLabel,
                menuLabel, orderNumberLabel, meals, drinks, sides, done, drinkGroup, mealGroup,
                sideGroup, icedGroup, hotDrinkGroup, hotMealGroup, coldMealGroup, addToOrder,
                quantitySlider, doorDRadio, onsiteRadio, phoneRadio, driveRadio, cancelButton,
                enterPhone, getPhoneNumber);

        return g;
    }

    /**
     * Sets the menu radio buttons.
     */
    private void setMenu(){
        //  Creates toggle groups
        menuToggleG1 = new ToggleGroup( );
        drinkToggleG = new ToggleGroup( );
        mealToggleG = new ToggleGroup();
        sidesToggleG = new ToggleGroup();
        icedToggleG = new ToggleGroup();
        hotDrinkToggleG = new ToggleGroup();
        coldMealToggle = new ToggleGroup();
        hotMealToggle = new ToggleGroup();
        orderTypeToggle = new ToggleGroup();


        // First layer of menu
        drinks = new RadioButton("Drinks");
        meals = new RadioButton("Meals");
        sides = new RadioButton("Sides");
        done = new RadioButton("Collapse");

        drinks.setToggleGroup(menuToggleG1);
        meals.setToggleGroup(menuToggleG1);
        sides.setToggleGroup(menuToggleG1);
        done.setToggleGroup(menuToggleG1);
        menuToggleG1.selectToggle(done);

        //  Second layer of menu
        hotDrink = new RadioButton("Hot Drinks");
        coldDrink = new RadioButton("Cold Drinks");
        hotMeal = new RadioButton("Hot Meal");
        coldMeal = new RadioButton("Cold Meal");
        fries = new RadioButton("Fries");
        friedOreos = new RadioButton("Fried Oreos");
        pickles = new RadioButton("Pickles");

        hotDrink.setToggleGroup(drinkToggleG);
        coldDrink.setToggleGroup(drinkToggleG);
        hotMeal.setToggleGroup(mealToggleG);
        coldMeal.setToggleGroup(mealToggleG);
        fries.setToggleGroup(sidesToggleG);
        pickles.setToggleGroup(sidesToggleG);
        friedOreos.setToggleGroup(sidesToggleG);

        //  The third layer of menu
        icedTea = new RadioButton("Iced Tea");
        water = new RadioButton("Water");
        hotCoffee = new RadioButton("Hot Coffee");
        hotChoco = new RadioButton("Hot Chocolate");

        icedTea.setToggleGroup(icedToggleG);
        water.setToggleGroup(icedToggleG);
        hotCoffee.setToggleGroup(hotDrinkToggleG);
        hotChoco.setToggleGroup(hotDrinkToggleG);

        pineapplePizza = new RadioButton("Pineapple Pizza");
        cheeseBurger = new RadioButton("Cheeseburger");
        watermelonSalad = new RadioButton("Watermelon Salad");
        icedNoodles = new RadioButton("Iced Noodles");

        pineapplePizza.setToggleGroup(hotMealToggle);
        cheeseBurger.setToggleGroup(hotMealToggle);
        watermelonSalad.setToggleGroup(coldMealToggle);
        icedNoodles.setToggleGroup(coldMealToggle);

        //  Handles the order type radio buttons.
        onsiteRadio = new RadioButton("Onsite");
        phoneRadio = new RadioButton("Phone");
        driveRadio = new RadioButton("Drivethrough");
        doorDRadio = new RadioButton("DoorDash");

        onsiteRadio.setToggleGroup(orderTypeToggle);
        phoneRadio.setToggleGroup(orderTypeToggle);
        doorDRadio.setToggleGroup(orderTypeToggle);
        driveRadio.setToggleGroup(orderTypeToggle);

        orderTypeToggle.selectToggle(onsiteRadio);

        //  Handles the groups
        drinkGroup = new Group(hotDrink, coldDrink);
        mealGroup = new Group( hotMeal, coldMeal );
        sideGroup = new Group( fries, friedOreos, pickles);
        icedGroup = new Group( icedTea, water );
        hotDrinkGroup = new Group( hotChoco, hotCoffee );
        hotMealGroup = new Group( pineapplePizza, cheeseBurger );
        coldMealGroup = new Group( icedNoodles, watermelonSalad );

        sideGroup.setVisible(false);
        drinkGroup.setVisible(false);
        mealGroup.setVisible(false);
        icedGroup.setVisible(false);
        hotDrinkGroup.setVisible(false);
        coldMealGroup.setVisible(false);
        hotMealGroup.setVisible(false);
    }


    private void setActions() {

        cancelButton.setText( "Cancel Order #" );

        cancelButton.setOnAction(orderController);
        submitButton.setOnAction(orderController);
        nameField.setOnAction(orderController);
        hotDrink.setOnAction(orderController);
        coldDrink.setOnAction(orderController);
        hotMeal.setOnAction(orderController);
        coldDrink.setOnAction(orderController);
        done.setOnAction(orderController);
        drinks.setOnAction(orderController);
        meals.setOnAction(orderController);
        sides.setOnAction(orderController);
        addToOrder.setOnAction(orderController);
        toggleView.setOnAction(orderController);
        getPhoneNumber.setOnAction(orderController);
        phoneRadio.setOnAction(orderController);
    }

    /**
     * Sets the quantitySlider
     */
    private void setSlider(){
        quantitySlider.setMax(5);
        quantitySlider.setMin(1);
        quantitySlider.setMajorTickUnit(1);
        quantitySlider.setShowTickLabels(true);
        quantitySlider.setShowTickMarks(true);
        quantitySlider.setSnapToTicks(true);
        quantitySlider.setMinorTickCount(0);
        quantitySlider.setValue(1);

    }


    public Button getCancel(){return cancelButton;}

    /**
     * Gets the submit button.
     *
     * @return  Submit button
     */
    public Button getSubmit(){ return submitButton; }

    /**
     * Gets the nameField
     * @return  nameField
     */
    public TextField getNameButton(){ return nameField; }

    /**
     * Gets the order number
     * @return  the order number
     */
    public Label getOrderNumber(){ return orderNumberLabel; }

    /**
     * Increments the order number
     */
    public void addOrderNumber(){
        int num = Integer.parseInt( orderNumberLabel.getText() ) + 1;
        orderNumberLabel.setText( String.valueOf( num ) );
    }

    /**
     * @return  The first menu toggle group
     */
    public RadioButton getDrinkButton(){ return drinks; }

    /**
     * @return  the coldDrink button
     */
    public RadioButton getColdDrink(){ return coldDrink; }

    public Group getIcedGroup(){ return icedGroup; }

    /**
     * @return  Gets the done button
     */
    public RadioButton getDoneButton(){ return done; }

    /**
     * @return  the meal radio button
     */
    public RadioButton getMealButton(){ return meals; }

    /**
     * @return  get sides button
     */
    public RadioButton getSidesButton(){ return sides; }

    /**
     * @return  the drinkGroup Group
     */
    public Group getDrinkGroup(){ return drinkGroup; }

    /**
     * @return  the mealGroup group
     */
    public Group getMealGroup(){ return mealGroup; }

    /**
     * @return  the sideGroup group
     */
    public Group getSideGroup(){ return sideGroup; }

    /**
     * @return  the hot drink radio button
     */
    public RadioButton getHotDrink(){ return hotDrink; }

    /**
     * @return  the HotDrinkGroup
     */
    public Group getHotDrinkGroup(){ return hotDrinkGroup; }

    /**
     * @return  the hotMeal button
     */
    public RadioButton getHotMeal(){ return hotMeal; }

    /**
     * @return  the hotMealGroup group
     */
    public Group getHotMealGroup(){ return hotMealGroup; }

    /**
     * @return  the coldMeal button
     */
    public RadioButton getColdMeal(){ return coldMeal; }

    /**
     * @return the coldMealGroup
     */
    public Group getColdMealGroup(){ return coldMealGroup; }

    /**
     * @return  addToOrder
     */
    public Button getAddToOrder() { return addToOrder; }

    /**
     * @return  gets the quantitySlider
     */
    public Slider getQuantitySlider(){ return quantitySlider; }

    /**
     * Adds to itemID
     * @param ID  Holds the int for itemID
     */
    public void addItemID(int ID) { this.itemID.add( ID ); }

    /**
     * @return  itemID array list
     */
    public ArrayList<Integer> getItemID() { return itemID; }

    /**
     * Adds to itemQuantity
     * @param quantity  Holds int
     */
    public void addItemQuantity( int quantity ) { this.itemQuantity.add( quantity ); }

    /**
     * @return  itemQuantity array list
     */
    public ArrayList<Integer> getItemQuantity() { return itemQuantity; }

    /**
     * @return  icedTea RadioButton
     */
    public RadioButton getIcedTea() { return icedTea; }

    /**
     * @return  priceLabel
     */
    public Label getPriceLabel() { return priceLabel; }

    /**
     * @return  water RadioButton
     */
    public RadioButton getWater() { return water; }

    /**
     * @return  icedNoodles Radiobutton
     */
    public RadioButton getIcedNoodles() { return icedNoodles; }

    /**
     * @return  hotCoffee radiobutton
     */
    public RadioButton getHotCoffee() { return hotCoffee; }

    /**
     * @return  hotChoco
     */
    public RadioButton getHotChoco() { return hotChoco; }

    /**
     * @return  watermelonSalad
     */
    public RadioButton getWatermelonSalad() { return watermelonSalad; }
    public void setCancelField(String text){
        cancelField.setText(text);
    }

    /**
     * @return  pineapplePizza
     */
    public RadioButton getPineapplePizza() { return pineapplePizza; }

    /**
     * @return  cheeseBurger
     */
    public RadioButton getCheeseBurger() { return cheeseBurger; }

    /**
     * @return  fries
     */
    public RadioButton getFries() { return fries; }

    /**
     * @return  friedOreos
     */
    public RadioButton getFriedOreos() { return friedOreos; }

    /**
     * @return  pickles
     */
    public RadioButton getPickles() { return pickles; }

    /**
     * @return  onsiteRadio
     */
    public RadioButton getOnsiteRadio() { return onsiteRadio; }

    /**
     * @return  doorDRadio
     */
    public RadioButton getDoorDRadio() { return doorDRadio; }

    /**
     * @return  phoneRadio
     */
    public RadioButton getPhoneRadio() { return phoneRadio; }

    /**
     * @return  driveRadio
     */
    public RadioButton getDriveRadio() { return driveRadio; }

    /**
     * @return  getPhoneNumber
     */
    public TextField getGetPhoneNumber() { return getPhoneNumber; }

    /**
     * @return  enterPhone
     */
    public Label getEnterPhone() { return enterPhone; }

    public Scene getScene(){
        return firstScene;
    }

    public void toggle(){
        if( toggle ) scene = getScene();
        else scene = cookView.getSecondScene();

        stage.setScene(scene);

        setToggle();
    }

    public void setScene(){
        firstScene = new Scene( group, 600,600 );
    }

    public void setToggle(){ toggle = !toggle; }

    public String getCanceledOrder(){return cancelField.getText();}

    public Button getToggleView() { return toggleView; }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
