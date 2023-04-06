package View;

import Controller.OrderController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerView extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

    }


    public static void main(String[] args) {
        CustomerView cv = new CustomerView();
        Application.launch( args );
    }


}
