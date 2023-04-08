package Model;

import java.io.IOException;

public class DoorDash extends Order {

    public DoorDash( String name, int orderNumber ) throws IOException {

        super( name, orderNumber );

        super.orderType = 1;
    }
}
