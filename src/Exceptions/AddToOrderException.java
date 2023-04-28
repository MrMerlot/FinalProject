package Exceptions;

import View.CustomerView;

public class AddToOrderException extends Exception{
    public AddToOrderException(){
        super();
        System.out.println("Invalid selection: Cannot add to order");
    }
}
