package Model;

public class Phone extends Order{

    protected String phoneNumber;
    public Phone(String name) {
        super(name);
        super.orderType = 3;
    }
}
