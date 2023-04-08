package Model;

import java.io.IOException;

public class Phone extends Order{

    protected String phoneNumber;
    public Phone(String name, int orderNumber ) throws IOException {
        super(name,orderNumber);
        super.orderType = 3;
    }
}
