package Exceptions;

import View.CustomerView;

public class AddToOrderException extends Exception{
    public AddToOrderException(){
        super();
        CustomerView.setExceptionLabel("Cannot be added to order due to invalid selection");
        System.out.println("Cannot be added to order due to invalid selection");

    }
}
