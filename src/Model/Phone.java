package Model;


public class Phone extends Order{
    private String phoneNumber;
    public Phone(String name, int orderNumber, String phone ) {
        super(name,orderNumber, 3);
        phoneNumber = phone;
    }
    public int getPhoneNumber() {
        if (getOrderType()==3){

            return Integer.parseInt(phoneNumber);
        }
        else{return 0;}
    }
}
