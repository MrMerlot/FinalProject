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

    /**
     * Get item ID at index
     *
     * @param index  Index of wanted item
     * @return  The item's iD
     */
    public int getItemID( int index ) { return itemID.get(index); }

    /**
     * Get the item's quantity at an index
     *
     * @param index  Item quantity's index
     * @return  The quantity
     */
    public int getItemQuantity( int index ){ return quantity.get( index ); }

    /**
     * Checks if the order is complete or not.
     *
     * @return  True or false
     */
    public boolean checkComplete() { return complete; }

    /**
     * Sets the order as complete
     */
    public void completeOrder() { complete = true; }

    public String orderInfo(){


    }
}
