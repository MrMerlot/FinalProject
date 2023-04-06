package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderData {

    private Queue<Order> doorDashQueue = new LinkedList<>();
    private Queue<Order> driveThroughQueue = new LinkedList<>();
    private Queue<Order> onSiteQueue = new LinkedList<>();
    private Queue<Order> phoneQueue = new LinkedList<>();

    protected PriorityQueue<Order> orderPriorityQueue = new PriorityQueue<>();


    public void setDoorDashQueue( Order doorDashOrder ){
        this.doorDashQueue.add( doorDashOrder );
    }

    public void setDriveThroughQueue( Order driveThroughOrder ){
        this.driveThroughQueue.add( driveThroughOrder );
    }

    /**
     *
     * @param onSiteOrder
     */
    public void setOnSiteQueue( Order onSiteOrder ){
        this.onSiteQueue.add( onSiteOrder );
    }

    /**
     * Adds a phpne order to the phoneOrderQueue
     * @param phoneOrder Order
     */
    public void setPhoneQueue( Order phoneOrder ){
        this.setPhoneQueue( phoneOrder );
    }

    /**
     * Displays the next order to be cooked
     */
    public void getNextOrder(){
        orderPriorityQueue.peek();
    }

}
