package Model;

import java.io.IOException;

public class Phone extends Order{
    protected String phoneNumber;
    public Phone(String name, int orderNumber, String phone ) {
        super(name,orderNumber, 3);
        phoneNumber = phone;
    }
    public int getPhoneNumber() {
        if (orderType==3){

            return Integer.parseInt(phoneNumber);
        }
        else{return 0;}
    }
}
