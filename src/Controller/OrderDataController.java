package Controller;
import Model.HashTableID;
import Model.Order;
import Model.OrderData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OrderDataController {
    OrderData orderData = new OrderData();
    OrderController orderController;
    CookController cookController;
    public OrderDataController(OrderController orderController){
        this.orderController = orderController;
    }
    public OrderDataController(CookController cookController){
        this.cookController = cookController;
    }

    /**
     * sets the current order to the next order and calls function to replace next order
     */
    public void setCurrentOrder(){
        if(!orderData.getNextOrder().equals("")) {
            orderData.setCurrentOrderObject(orderData.getNextOrderObject());
            orderData.setCurrentOrder(orderData.getNextOrder());
        }
        setNextOrder();
    }

    /**
     * Checks if the next order skipped over any lower numbered orders
     */
    public void checkSkipped(){
        if(!orderData.getOnSiteQueue().isEmpty()) {
            if (orderData.getOnSiteQueue().peek().getOrderNumber() < orderData.getNextOrderObject().getOrderNumber()) {
                orderData.getOnSiteQueue().peek().setSkipped(orderData.getOnSiteQueue().peek().getIfSkipped() + 1);
            }
        }
        if(!orderData.getPhoneQueue().isEmpty()) {
            if (orderData.getPhoneQueue().peek().getOrderNumber() < orderData.getNextOrderObject().getOrderNumber()) {
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1);
            }
        }
        if(!orderData.getDoorDashQueue().isEmpty()) {
            if (orderData.getDoorDashQueue().peek().getOrderNumber() < orderData.getNextOrderObject().getOrderNumber()) {
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);
            }
        }
    }
    /**
     * Sets nextOrder to its appropriate value
     * Checks to see if current order is empty and sets it
     */
    public void setNextOrder(){
        HashTableID hashTable = new HashTableID();
        String input = "";
        ArrayList<Integer> itemID = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        if(!orderData.getDriveThroughQueue().isEmpty()){        //if the DT queue isnt empty
            input += orderData.getDriveThroughQueue().peek().getName() + "\n";
            itemID = orderData.getDriveThroughQueue().peek().getItemID();
            quantities = orderData.getDriveThroughQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){      //read nessesary information into input string and assign to nextOrder
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getDriveThroughQueue().remove());    //removes the object from queue and assigns it to next order object
            checkSkipped();
        }
        else if(!orderData.getOnSiteQueue().isEmpty()){     //if O queue isnt empty
            input += orderData.getOnSiteQueue().peek().getName() + "\n";
            itemID = orderData.getOnSiteQueue().peek().getItemID();
            quantities = orderData.getOnSiteQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());
            checkSkipped();
        }
        else if(!orderData.getPhoneQueue().isEmpty()){      //if P queue isnt empty
            input += orderData.getPhoneQueue().peek().getName() + "\n";
            itemID = orderData.getPhoneQueue().peek().getItemID();
            quantities = orderData.getPhoneQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getPhoneQueue().remove());
            checkSkipped();
        }
        else if(!orderData.getDoorDashQueue().isEmpty()){  //if DD queue isnt empty
            input += orderData.getDoorDashQueue().peek().getName() + "\n";
            itemID = orderData.getDoorDashQueue().peek().getItemID();
            quantities = orderData.getDoorDashQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getDoorDashQueue().remove());
        }
        orderData.setNextOrder(input);
        if(orderData.getCurrentOrder().equals("") && !orderData.getNextOrder().equals("")){
            setCurrentOrder();
        }
    }

    /**
     * Makes sure the next order is the highest priority
     */
    public void replaceNextOrder() {
        Queue<Order> temp = new LinkedList<>();
        if (orderData.getNextOrderObject().getOrderType() == 2 && orderData.getNextOrderObject().getIfSkipped() < 3) {//the next order is lower priority then a queued Order
            if (!orderData.getDriveThroughQueue().isEmpty()) {               //if the DT Queue has an object
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getOnSiteQueue().isEmpty()) {
                    temp.add(orderData.getOnSiteQueue().remove());
                }
                while (!temp.isEmpty()) {
                    orderData.setOnSiteQueue(temp.remove());
                }
                orderData.getOnSiteQueue().peek().setSkipped(orderData.getOnSiteQueue().peek().getIfSkipped() + 1);
            }
        } else if (orderData.getNextOrderObject().getOrderType() == 3 && orderData.getNextOrderObject().getIfSkipped() < 3) { //the next order is lower priority then a queued Order
            if (!orderData.getDriveThroughQueue().isEmpty()) {                //if DT queue has an object
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getPhoneQueue().isEmpty()) {
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while (!temp.isEmpty()) {
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1);//increase skips on previous next order by one
            } else if (!orderData.getOnSiteQueue().isEmpty()) {              //if O queue has an object
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getPhoneQueue().isEmpty()) {
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while (!temp.isEmpty()) {
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1);//increase skips on previous next order by one
            }

        } else if (orderData.getNextOrderObject().getOrderType() == 4 && orderData.getNextOrderObject().getIfSkipped() < 3) {//the next order is lower priority then a queued Order
            if (!orderData.getDriveThroughQueue().isEmpty()) {  //if the DT queue has an object
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getDoorDashQueue().isEmpty()) {
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while (!temp.isEmpty()) {
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);//increase skips on previous next order by one
            } else if (!orderData.getOnSiteQueue().isEmpty()) {   //if the onsite queue has on object
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getDoorDashQueue().isEmpty()) {
                    temp.add(orderData.getDoorDashQueue().remove());
                }
                while (!temp.isEmpty()) {
                    orderData.setDoorDashQueue(temp.remove()); //puts the next order back at the start of the DD queue
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);//increase skips on previous next order by one
            } else if (!orderData.getPhoneQueue().isEmpty()) {     //if the phone queue has an object
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getDoorDashQueue().isEmpty()) {
                    temp.add(orderData.getDoorDashQueue().remove());
                }
                while (!temp.isEmpty()) {
                    orderData.setDoorDashQueue(temp.remove()); //puts the next order back at the start of the DD queue
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);//increase skips on previous next order by one
            }
        }
    }

    /**
     * Makes sure next and current order have an object, calls respective setter if not
     * If both do, makes sure the next order is the highest priority order
     * If not, puts next order back into respective queue and sets next order
     */
    public void checkQueue(){
        if(!orderData.getCurrentOrder().equals("")) {
            if (orderData.getNextOrder().equals("")) {   //checks for a next order object, if none set the next order
                setNextOrder();
                return;
            }
            else if (orderData.getCurrentOrder().equals("")) { //checks for a current order object, if none set the current order
                setCurrentOrder();
                return;
            }
        }
        replaceNextOrder();
        if(orderData.getNextOrder().equals("")) {
            setNextOrder();
        }
    }

    /**
     * Removes the requested order from OrderData unless its complete or in progress
     *
     * @param order
     */
    public void cancelOrder(Order order){
        if(orderData.getNextOrderObject().getOrderNumber() == order.getOrderNumber()){//requested cancel is the next order
            orderData.setNextOrder(null);
            setNextOrder();
        }
        else if(orderData.getDriveThroughQueue().contains(order)){
            orderData.getDriveThroughQueue().remove(order);
        }
        else if(orderData.getOnSiteQueue().contains(order)){
            orderData.getOnSiteQueue().remove(order);
        }
        else if(orderData.getPhoneQueue().contains(order)){
            orderData.getPhoneQueue().remove(order);
        }
        else if(orderData.getDoorDashQueue().contains(order)){
            orderData.getDoorDashQueue().remove(order);
        }
    }

    /**
     * Returns true if all four queues are empty, false otherwise
     *
     * @return boolean
     */
    public boolean isEmpty(){
        if(orderData.getDriveThroughQueue().isEmpty() && orderData.getOnSiteQueue().isEmpty()
                && orderData.getPhoneQueue().isEmpty() && orderData.getDoorDashQueue().isEmpty()) {
            return true;
        }
        return false;
    }
}