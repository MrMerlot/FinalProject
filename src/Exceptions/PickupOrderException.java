package Exceptions;

public class PickupOrderException extends Exception{
    public PickupOrderException(){
        super();
        System.out.println( "No selected pickup order" );
    }
}
