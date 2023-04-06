package Model;

public class DoorDash extends Order {

    public DoorDash( String name ){

        super( name );

        super.orderType = 1;
    }
}
