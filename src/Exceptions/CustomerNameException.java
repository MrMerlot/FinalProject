package Exceptions;

import View.CustomerView;

public class CustomerNameException extends Exception{
    public CustomerNameException(){
        super();
        System.out.println("Enter a name before submitting your order");
    }
}
