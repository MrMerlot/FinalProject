package Model;

public class Onsite extends Order{
    public Onsite(String name, int orderNumber) {
        super(name, orderNumber);
        super.orderType =4;
    }
}
