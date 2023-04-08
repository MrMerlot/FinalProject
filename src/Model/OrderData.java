package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderData {

    private Queue< Order > doorDashQueue = new LinkedList<>();                        //stores all door dash orders
    private Queue< Order > driveThroughQueue = new LinkedList<>();                    //stores all drive through orders
    private Queue< Order > onSiteQueue = new LinkedList<>();                          //stores all onsite orders
    private Queue< Order > phoneQueue = new LinkedList<>();                           //stores all phone orders
    protected PriorityQueue< Order > orderPriorityQueue = new PriorityQueue<>();      //what will be cooked next


    private ArrayList< Order > completedOrders = new ArrayList<>();                           //where completed orders are stored

    /**
     * Adds a door dash order to doorDashQueue
     * @param doorDashOrder Order
     */
    public void setDoorDashQueue( Order doorDashOrder ){
        this.doorDashQueue.add( doorDashOrder );
    }

    /**
     * Adds a drivethrough order to the driveThroughQueue
     * @param driveThroughOrder Order
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


}
