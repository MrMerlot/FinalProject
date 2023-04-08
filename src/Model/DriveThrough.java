package Model;

import java.io.IOException;

public class DriveThrough extends Order {

    public DriveThrough( String name, int orderNumber ) throws IOException {

        super(name, orderNumber);

        super.orderType = 2;
    }
}
