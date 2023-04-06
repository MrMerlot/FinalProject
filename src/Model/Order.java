package Model;

import java.util.ArrayList;

public class Order {

    protected int orderNumber;
    protected ArrayList<Integer> itemID = new ArrayList<>();
    protected ArrayList<Integer> quantity = new ArrayList<>();
    protected int orderType;
    protected boolean complete;
    protected String orderInfo;
    protected String name;

    public Order( String name ){

        this.name = name;
    }
}
