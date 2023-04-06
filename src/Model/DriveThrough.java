package Model;

public class DriveThrough extends Order {

    public DriveThrough( String name ){

        super(name);

        super.orderType = 2;
    }
}
