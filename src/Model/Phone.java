package Model;

import java.io.IOException;

public class Phone extends Order{

    protected String phoneNumber;
    public Phone(String name, int orderNumber, int orderType ) {
        super(name,orderNumber, orderType);
    }
}
