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

    /**
     * Creeates an order with a name and number.
     *
     * @param name  Customer name
     * @param orderNumber  Order's number
     */
    public Order( String name, int orderNumber ){

        this.name = name;
        this.orderNumber = orderNumber;
    }

    /**
     * Adds an item to the order.
     *
     * @param itemID  The item's ID
     * @param quantity  How many items ordered
     */
    public void addItem( int itemID, int quantity ){

        this.itemID.add(itemID);
        this.quantity.add(quantity);
    }


}
