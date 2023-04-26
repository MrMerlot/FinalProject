package Controller;

public class PickupOrderException extends Exception{
    public PickupOrderException(){
        super();
        System.out.println( "There is no orders to be picked up." );
    }
}
