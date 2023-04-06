package Model;

public class DriveThrough extends Order {

    public DriveThrough( String name, int orderNumber ){

        super(name, orderNumber);

        super.orderType = 2;
    }
}
