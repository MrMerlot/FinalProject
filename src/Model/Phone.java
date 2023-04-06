package Model;

public class Phone extends Order{

    protected String phoneNumber;
    public Phone(String name, int orderNumber ) {
        super(name,orderNumber);
        super.orderType = 3;
    }
}
