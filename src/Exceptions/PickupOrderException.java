package Exceptions;

public class PickupOrderException extends Exception{
    public PickupOrderException(){
        super();
        System.out.println( "There are no orders to be picked up." );
    }
}
