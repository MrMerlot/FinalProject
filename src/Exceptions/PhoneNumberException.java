package Exceptions;

import Model.Phone;

public class PhoneNumberException extends Exception{
    public PhoneNumberException(){
        super();
        System.out.println("Please enter a valid phone number(numbers only) in the provided box");
    }
}
