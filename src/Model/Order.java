package Model;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Order extends FileWriter {
    public static File file = new File("savedData");
    FileWriter fileWriter = new FileWriter("savedData");
    protected int orderNumber;
    protected ArrayList<Integer> itemID = new ArrayList<>();
    protected ArrayList<Integer> quantity = new ArrayList<>();
    protected int orderType;
    protected boolean complete;
    protected String orderInfo;
    protected String name;
    protected int skipped = 0;

    /**
     * Creeates an order with a name and number.
     *
     * @param name  Customer name
     * @param orderNumber  Order's number
     */
    public Order( String name, int orderNumber ) throws IOException {
        super(file);
        fileWriter.write("HEADER");
        fileWriter.write(orderType);
        for(int i=0;i<itemID.size();i++){fileWriter.write(itemID.get(i));}
        for(int i=0;i<quantity.size();i++){fileWriter.write(quantity.get(i));}
        fileWriter.write(orderNumber);
        fileWriter.write(name);
        if(complete) {fileWriter.write("Completed");}

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

        return null;
    }
}
