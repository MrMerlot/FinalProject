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
    protected static PriorityQueue< Order > orderPriorityQueue = new PriorityQueue<>();//what will be cooked next


    private static ArrayList< String > pickupOrders = new ArrayList<>();          //where completed orders are stored

    /**
     * Adds a door dash order to doorDashQueue
     * @param doorDashOrder DD Order
     */
    public void setDoorDashQueue( Order doorDashOrder ){
        this.doorDashQueue.add( doorDashOrder );
    }

    /**
     * Adds a drivethrough order to the driveThroughQueue
     * @param driveThroughOrder DT Order
     */
    public void setDriveThroughQueue( Order driveThroughOrder ){
        this.driveThroughQueue.add( driveThroughOrder );
    }

    /**
     * Adds a onsite order to the onSiteOrderQueue
     * @param onSiteOrder Order
     */
    public void setOnSiteQueue( Order onSiteOrder ){
        this.onSiteQueue.add( onSiteOrder );
    }

    /**
     * Adds a phone order to the phoneOrderQueue
     * @param phoneOrder Order
     */
    public void setPhoneQueue( Order phoneOrder ){
        this.phoneQueue.add( phoneOrder );
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

}
