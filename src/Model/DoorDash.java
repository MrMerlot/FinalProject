package Model;

public class DoorDash extends Order {

    public DoorDash( String name, int orderNumber ){

        super( name, orderNumber );

        super.orderType = 1;
    }
}
