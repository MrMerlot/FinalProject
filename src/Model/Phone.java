package Model;

import java.io.IOException;

public class Phone extends Order{

    protected String phoneNumber;
    public Phone(String name, int orderNumber) {
        super(name,orderNumber, 3);
    }
}
