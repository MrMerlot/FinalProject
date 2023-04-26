package Controller;

public class PickupOrderException extends Exception{
    public PickupOrderException(){
        super( "There is no orders to be picked up." );
    }
}
