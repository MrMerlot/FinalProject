package Model;

import java.util.*;

public class OrderData {

    private static Queue< Order > doorDashQueue = new LinkedList<>();                 //stores all door dash orders
    private static Queue< Order > driveThroughQueue = new LinkedList<>();             //stores all drive through orders
    private static Queue< Order > onSiteQueue = new LinkedList<>();                   //stores all onsite orders
    private static Queue< Order > phoneQueue = new LinkedList<>();                    //stores all phone orders

    private static Order currentOrderObject;                                            //Object of Current Order

    private static Order nextOrderObject;                                              //Object of Next Order
    private static String currentOrder = "";                                           //String for the current order
    private static String nextOrder = "";                                                //String for the next order

    private static HashMap<Integer,String> itemIDNames = new HashMap<>();               //Hashmap for Item Names
    private static HashMap<Integer,Integer> itemIDPrice = new HashMap<>();              //Hashmap for Item Prices


    private static ArrayList< String > pickupOrders = new ArrayList<>();          //where completed orders are stored

    public void addOrder( Order order ) {
        if (order instanceof DoorDash) this.doorDashQueue.add(order);
        else if (order instanceof DriveThrough) this.driveThroughQueue.add( order );
        else if (order instanceof  Onsite ) this.onSiteQueue.add( order );
        else this.phoneQueue.add( order );

    }

    /**
     * Adds a Door Dash Order to the Door Dash Queue
     * @param DDOrder Door Dash Order
     */
    public void setDoorDashQueue(Order DDOrder){
        doorDashQueue.add(DDOrder);
    }

    /**
     * Gets the Door Dash
     * @return Door Dash Order Queue
     */
    public Queue<Order> getDoorDashQueue(){
        return doorDashQueue;
    }


    /**
     * Adds a OnSite Order to the OnSite Queue
     * @param OSOrder OnSite Order
     */
    public void setOnSiteQueue(Order OSOrder){
        onSiteQueue.add(OSOrder);
    }

    /**
     * Gets the DriveThrough Queue
     * @return DriveThrough Queue
     */
    public Queue<Order> getDriveThroughQueue(){
        return driveThroughQueue;
    }

    /**
     * Adds a Drive Through Order to DriveThrough Queue
     * @param DTOrder DriveThrough Order
     */
    public void setDriveThroughQueue(Order DTOrder){
        driveThroughQueue.add(DTOrder);
    }

    /**
     * Gets the OnSite Queue
     * @return OnSite Queue
     */
    public Queue<Order> getOnSiteQueue(){
        return onSiteQueue;
    }


    /**
     * Adds a Phone Order to the DriveThrough Queue
     * @param POrder Phone Order
     */
    public void setPhoneQueue(Order POrder){
        phoneQueue.add(POrder);
    }

    /**
     * Gets the Phone Queue
     * @return Phone Queue
     */
    public Queue<Order> getPhoneQueue(){
        return phoneQueue;
    }

    /**
     * Adds an order to the pickup ArrayList
     * @param order String
     */
    public void setPickupOrders(String order){
        pickupOrders.add(order);
    }


    /**
     * Removes the order that has been picked up
     * @param rpu String
     */
    public void removePickUpOrder(String rpu){
        pickupOrders.remove(rpu);

    }

    /**
     * Gets the ArrayList for the Pickup Orders
     * @return ArrayList
     */
    public ArrayList<String> getPickupOrders(){
        return pickupOrders;
    }

    /**
     * Sets the next order
     * @param nxtOrder String
     */
    public void setNextOrder(String nxtOrder){
        nextOrder = nxtOrder;
    }

    /**
     * Gets the next order
     * @return String
     */
    public String getNextOrder(){
        return nextOrder;
    }

    /**
     * Sets the current order
     * @param currntOrder
     */
    public void setCurrentOrder(String currntOrder){
        currentOrder = currntOrder;
    }

    /**
     * Get the Current Order
     * @return String
     */
    public String getCurrentOrder(){
        return currentOrder;
    }

    /**
     * Adds an item to the menu
     * @param itemID Int
     * @param itemName String
     */
    public void setItemIDNames(int itemID, String itemName){
        itemIDNames.put(itemID,itemName);
    }


    /**
     * returns the wanted Item from the HashMap
     * @param itemId Int
     * @return String
     */
    public String getItemIDNames(int itemId){
        return itemIDNames.get(itemId);
    }

    /**
     * Adds an items price to the menu
     * @param itemID Int
     * @param itemPrice Int
     */
    public void setItemIDPrice(int itemID, int itemPrice){
        itemIDPrice.put(itemID,itemPrice);
    }


    /**
     * returns the wanted Item Price from the HashMap
     * @param itemId Int
     * @return String
     */
    public int getItemIDPrice(int itemId){
        return itemIDPrice.get(itemId);
    }

    /**
     * Sets the next order Object
     * @param nxtOrderObject Order
     */
    public void setNextOrderObject(Order nxtOrderObject){
        nextOrderObject = nxtOrderObject;
    }

    /**
     * Gets the next order Object
     * @return String
     */
    public Order getNextOrderObject(){
        return nextOrderObject;
    }

    /**
     * Sets the current order Object
     * @param currntOrderObject
     */
    public void setCurrentOrderObject(Order currntOrderObject){
        currentOrderObject = currntOrderObject;
    }

    /**
     * Get the Current Order Object
     * @return String
     */
    public Order getCurrentOrderObject(){
        return currentOrderObject;
    }
}
