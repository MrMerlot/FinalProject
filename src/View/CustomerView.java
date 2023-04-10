package View;

import Controller.OrderController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomerView extends Application {
    private Label customerLabel = new Label("Customer");
    private Label orderLabel = new Label("Order # ");
    private Label orderNumberLabel = new Label(""+1);
    private Label promptLabel = new Label("Order Type? \n\nName?");
    private Label cancelLabel = new Label("Cancel Order #");
    private TextField cancelField = new TextField("__");
    private Button toggleView = new Button("Switch to Cook View");
    private Button submitButton = new Button("Submit Order");
    private TextField nameField = new TextField();
    private TextField typeField = new TextField();
    private Label priceLabel = new Label("Price");
    private Label menuLabel = new Label("Menu:");
    private CookView cookView = new CookView();
    private Scene scene;
    private Group group, drinkGroup, mealGroup, sideGroup, icedGroup,
    hotDrinkGroup;
    private RadioButton hotDrink, coldDrink, hotMeal, coldMeal, fries,
            pickles, friedOreos, drinks, meals, sides, done, icedTea, water,
            hotCoffee, hotChoco;
    private ToggleGroup menuToggleG1, drinkToggleG, mealToggleG, sidesToggleG,
            icedToggleG, hotDrinkToggleG;

    //  Connects CustomerView to OrderController
    private OrderController orderController = new OrderController(this);


    @Override
    public void start(Stage primaryStage) throws Exception {

        setMenu();
        setPositions();
        setActions();



        group = addNodes();

        scene = new Scene(group, 1000,600);

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
    }

    /**
     * Adds all the nodes to a group.
     *
     * @return  The group containing the nodes.
     */
    private Group addNodes(){
        Group g = new Group();
        g.getChildren().addAll(customerLabel, orderLabel, promptLabel,
                cancelField, toggleView, submitButton, cancelLabel, nameField,
                typeField, priceLabel, menuLabel, orderNumberLabel, meals,
                drinks, sides, done, drinkGroup, mealGroup, sideGroup, icedGroup, hotDrinkGroup);

        return g;
    }

    /**
     * Sets the menu radio buttons.
     */
    public void setMenu(){
        //  Creates togglegroups
        menuToggleG1 = new ToggleGroup();
        drinkToggleG = new ToggleGroup();
        mealToggleG = new ToggleGroup();
        sidesToggleG = new ToggleGroup();
        icedToggleG = new ToggleGroup();
        hotDrinkToggleG = new ToggleGroup();


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

        //  Handles the groups
        drinkGroup = new Group(hotDrink, coldDrink);
        mealGroup = new Group( hotMeal, coldMeal );
        sideGroup = new Group( fries, friedOreos, pickles);
        icedGroup = new Group( icedTea, water );
        hotDrinkGroup = new Group( hotChoco, hotCoffee );

        sideGroup.setVisible(false);
        drinkGroup.setVisible(false);
        mealGroup.setVisible(false);
        icedGroup.setVisible(false);
    }

    public void setActions(){
        submitButton.setOnAction( orderController );
        nameField.setOnAction( orderController );
        hotDrink.setOnAction( orderController );
        coldDrink.setOnAction( orderController );
        hotMeal.setOnAction( orderController );
        coldDrink.setOnAction( orderController );
        done.setOnAction( orderController );
        drinks.setOnAction( orderController );
        meals.setOnAction( orderController );
        sides.setOnAction( orderController );
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
     * Gets typeField
     * @return typeField
     */
    public TextField getOrderType(){ return typeField; }

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

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
