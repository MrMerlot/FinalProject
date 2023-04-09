package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderData {

    private static Queue< Order > doorDashQueue = new LinkedList<>();                 //stores all door dash orders
    private static Queue< Order > driveThroughQueue = new LinkedList<>();             //stores all drive through orders
    private static Queue< Order > onSiteQueue = new LinkedList<>();                   //stores all onsite orders
    private static Queue< Order > phoneQueue = new LinkedList<>();                    //stores all phone orders
    protected static PriorityQueue< Order > orderPriorityQueue = new PriorityQueue<>();  //what will be cooked next


    private static ArrayList< String > pickupOrders = new ArrayList<>();          //where completed orders are stored

    public void addOrder( Order order ) {
        if (order instanceof DoorDash) this.doorDashQueue.add(order);
        else if (order instanceof DriveThrough) this.driveThroughQueue.add( order );
        else if (order instanceof  Onsite ) this.onSiteQueue.add( order );
        else this.phoneQueue.add( order );
    }

    /**
     * Updates the orders that are next to be cooked
     * @param sortedOrder Order
     */
    public void setOrderPriorityQueue( Order sortedOrder ){
        this.setOrderPriorityQueue( sortedOrder );
    }
    /**
     * Displays the next order to be cooked
     */
    public void getNextOrder(){
        orderPriorityQueue.peek();
    }

    public void setPickupOrders(String order){
        pickupOrders.add(order);
    }

    public void removePickUpOrder(String rpu){
        pickupOrders.remove(rpu);

    }

    public ArrayList<String> getPickupOrders(){
        return pickupOrders;
    }
}
