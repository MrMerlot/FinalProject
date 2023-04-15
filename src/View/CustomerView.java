package View;

import Controller.OrderController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class CustomerView extends Application {
    private Label customerLabel = new Label("Customer");
    private Label orderLabel = new Label("Order # ");
    private Label orderNumberLabel = new Label(""+1);
    private Label promptLabel = new Label("Order Type? \n\n\n\n\nName?");
    private Label cancelLabel = new Label("Cancel Order #");
    private TextField cancelField = new TextField("");
    private Button toggleView = new Button("Switch to Cook View");
    private Button submitButton = new Button("Submit Order");
    private TextField nameField = new TextField();
    private Label priceLabel = new Label("Price");
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

    //  Connects CustomerView to OrderController
    private OrderController orderController = new OrderController(this);

    public CustomerView() {
        cookView = new CookView();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        setMenu();
        setPositions();
        setActions();
        setSlider();

        group = addNodes();

        scene = new Scene(group, 1000,600);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        System.out.println();
        primaryStage.setTitle("CustomerView");
        primaryStage.show();
    }

    /**
     * Sets the positions of buttons or labelsss
     */
    private void setPositions(){

        orderLabel.setLayoutX(900);
        orderNumberLabel.setLayoutX(950);
        promptLabel.setLayoutY(50);
        cancelLabel.setLayoutY(500);
        cancelLabel.setLayoutX(900);
        priceLabel.setLayoutX(470);
        priceLabel.setLayoutY(570);
        cancelField.setLayoutX(980);
        cancelField.setLayoutY(500);
        cancelField.setMaxWidth(20);
        nameField.setLayoutX(100);
        nameField.setLayoutY(135);
        toggleView.setLayoutY(570);
        submitButton.setLayoutX(900);
        submitButton.setLayoutY(570);
        addToOrder.setLayoutY(300);
        addToOrder.setLayoutX(160);
        quantitySlider.setLayoutY(305);
        quantitySlider.setLayoutX(10);

        menuLabel.setLayoutY(200);
        menuLabel.setLayoutX(40);
        drinks.setLayoutY(220);
        meals.setLayoutY(240);
        sides.setLayoutY(260);
        done.setLayoutY(280);

        coldDrink.setLayoutY(230);
        hotDrink.setLayoutY(250);
        coldDrink.setLayoutX(100);
        hotDrink.setLayoutX(100);

        coldMeal.setLayoutY(230);
        hotMeal.setLayoutY(250);
        coldMeal.setLayoutX(100);
        hotMeal.setLayoutX(100);

        fries.setLayoutY(220);
        friedOreos.setLayoutY(240);
        pickles.setLayoutY(260);
        fries.setLayoutX(100);
        friedOreos.setLayoutX(100);
        pickles.setLayoutX(100);

        icedTea.setLayoutY(220);
        water.setLayoutY(240);
        icedTea.setLayoutX(200);
        water.setLayoutX(200);
        hotCoffee.setLayoutY(220);
        hotChoco.setLayoutY(240);
        hotCoffee.setLayoutX(200);
        hotChoco.setLayoutX(200);

        pineapplePizza.setLayoutY(220);
        cheeseBurger.setLayoutY(240);
        icedNoodles.setLayoutY(220);
        watermelonSalad.setLayoutY(240);
        pineapplePizza.setLayoutX(200);
        cheeseBurger.setLayoutX(200);
        watermelonSalad.setLayoutX(200);
        icedNoodles.setLayoutX(200);

        onsiteRadio.setLayoutY(50);
        onsiteRadio.setLayoutX(100);
        phoneRadio.setLayoutY(70);
        phoneRadio.setLayoutX(100);
        doorDRadio.setLayoutY(90);
        doorDRadio.setLayoutX(100);
        driveRadio.setLayoutX(100);
        driveRadio.setLayoutY(110);
    }

    /**
     * Adds all the nodes to a group.
     *
     * @return  The group containing the nodes.
     */
    private Group addNodes(){
        Group g = new Group();
        g.getChildren().addAll(customerLabel, orderLabel, promptLabel,
                cancelField, toggleView, submitButton, cancelLabel, nameField, priceLabel,
                menuLabel, orderNumberLabel, meals, drinks, sides, done, drinkGroup, mealGroup,
                sideGroup, icedGroup, hotDrinkGroup, hotMealGroup, coldMealGroup, addToOrder,
                quantitySlider, doorDRadio, onsiteRadio, phoneRadio, driveRadio);

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
        done = new RadioButton("Done");

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

    public static void main(String[] args) {
        Application.launch(args);
    }
}
