package Model;

import java.io.IOException;

public class Onsite extends Order{
    public Onsite(String name, int orderNumber) throws IOException {
        super(name, orderNumber);
        super.orderType =4;
    }
}
