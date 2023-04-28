package Controller;
import Model.HashTableID;
import Model.Order;
import Model.OrderData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OrderDataController {
    //Fix
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
        if(!orderData.getNextOrder().equals("")) {                           //if next order is not empty
            orderData.setCurrentOrderObject(orderData.getNextOrderObject()); //sets current order Object
            orderData.setCurrentOrder(orderData.getNextOrder());             //sets current order string
        }
        setNextOrder();                                                      //calls set next order method
    }

    /**
     * Checks if the next order skipped over any lower numbered orders
     */
    public void checkSkipped(){
        if(!orderData.getOnSiteQueue().isEmpty()) { //if the onsite queue is not empty
            if (orderData.getOnSiteQueue().peek().getOrderNumber() < orderData.getNextOrderObject().getOrderNumber()) { //if the head of the onsitie queue has a smaller orderNum then the next object
                orderData.getOnSiteQueue().peek().setSkipped(orderData.getOnSiteQueue().peek().getIfSkipped() + 1); //increment head of onsite queue skip count by one
            }
        }
        if(!orderData.getPhoneQueue().isEmpty()) {
            if (orderData.getPhoneQueue().peek().getOrderNumber() < orderData.getNextOrderObject().getOrderNumber()) { //if the head of the Phone queue has a smaller orderNum then the next object
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1); //increment head of Phone queue skip count by one
            }
        }
        if(!orderData.getDoorDashQueue().isEmpty()) {
            if (orderData.getDoorDashQueue().peek().getOrderNumber() < orderData.getNextOrderObject().getOrderNumber()) { //if the head of the Phone queue has a smaller orderNum then the next object
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1); //increment head of DoorDash queue skip count by one
            }
        }
    }

    /**
     * Checks for any queue heads that have hit the maximum amount of skips and moves them to nextOrder
     * @param input
     * @return String
     */
    public String checkMaxSkipped(String input){
        HashTableID hashTable = new HashTableID();
        ArrayList<Integer> itemID = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        if(!orderData.getOnSiteQueue().isEmpty()) {
            if (orderData.getOnSiteQueue().peek().getIfSkipped() > 3) {//if onsite queue head has been skipped 3 times
                input += orderData.getOnSiteQueue().peek().getName() + "\n";
                itemID = orderData.getOnSiteQueue().peek().getItemID();
                quantities = orderData.getOnSiteQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) {  //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());//moves the OnSite queue head to next order
            }
            return input;
        }
        if(!orderData.getPhoneQueue().isEmpty()) {
            if (orderData.getPhoneQueue().peek().getIfSkipped() > 3) { //if Phone queue head has been skipped 3 times
                input += orderData.getPhoneQueue().peek().getName() + "\n";
                itemID = orderData.getPhoneQueue().peek().getItemID();
                quantities = orderData.getPhoneQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) {     //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getOnSiteQueue().remove()); //moves the Phone queue head to next order
            }
            return input;
        }
        if(!orderData.getDoorDashQueue().isEmpty()) {
            if (orderData.getDoorDashQueue().peek().getIfSkipped() > 3) { //if DoorDash queue head has been skipped 3 times
                input += orderData.getDoorDashQueue().peek().getName() + "\n";
                itemID = orderData.getDoorDashQueue().peek().getItemID();
                quantities = orderData.getDoorDashQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) {    //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());  //moves the DoorDash queue head to next order
            }
            return input;
        }
        return input;
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
        input = checkMaxSkipped(input);
        if(input.equals("")) {
            if (!orderData.getDriveThroughQueue().isEmpty()) {    //if the DT queue is not empty
                input += orderData.getDriveThroughQueue().peek().getName() + "\n";
                itemID = orderData.getDriveThroughQueue().peek().getItemID();
                quantities = orderData.getDriveThroughQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) { //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getDriveThroughQueue().remove());  //moves DriveThrough queue head to nextOrder
                checkSkipped();
            } else if (!orderData.getOnSiteQueue().isEmpty()) {     //if O queue is not empty
                input += orderData.getOnSiteQueue().peek().getName() + "\n";
                itemID = orderData.getOnSiteQueue().peek().getItemID();
                quantities = orderData.getOnSiteQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) {  //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());  //moves Onsite queue head to nextOrder
                checkSkipped();
            } else if (!orderData.getPhoneQueue().isEmpty()) {      //if P queue is not empty
                input += orderData.getPhoneQueue().peek().getName() + "\n";
                itemID = orderData.getPhoneQueue().peek().getItemID();
                quantities = orderData.getPhoneQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) { //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getPhoneQueue().remove());   //moves Phone queue head to nextOrder
                checkSkipped();
            } else if (!orderData.getDoorDashQueue().isEmpty()) {  //if DD queue is not empty
                input += orderData.getDoorDashQueue().peek().getName() + "\n";
                itemID = orderData.getDoorDashQueue().peek().getItemID();
                quantities = orderData.getDoorDashQueue().peek().getQuantities();
                for (int i = 0; i < itemID.size(); i++) { //stores all nessesary information about the order in input String
                    input += quantities.get(i) + " ";
                    input += hashTable.getItemIDName(itemID.get(i)) + "\n";
                }
                orderData.setNextOrderObject(orderData.getDoorDashQueue().remove());    //moves DoorDash queue head to nextOrder
                checkSkipped();
            }
        }
        orderData.setNextOrder(input); //assigns the input string to the nextOrderLabel
        if(orderData.getCurrentOrder().equals("") && !orderData.getNextOrder().equals("")){
            setCurrentOrder();  //if current order is empty but next order has an object, sets current order
        }
    }

    /**
     * Checks that the next order is the highest priority available
     * If not, put the next order object back in its queue
     */
    public void replaceNextOrder(){
        Queue<Order> temp = new LinkedList<>();
        if(orderData.getNextOrderObject().getOrderType() == 2 && orderData.getNextOrderObject().getIfSkipped() < 3){//if the next order is an O Object and has been skipped less than 3 times
            if(!orderData.getDriveThroughQueue().isEmpty()) {     //if the DT Queue is not empty
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while(!orderData.getOnSiteQueue().isEmpty()){
                    temp.add(orderData.getOnSiteQueue().remove());
                }
                while(!temp.isEmpty()){
                    orderData.setOnSiteQueue(temp.remove());         //puts the next order back into O queue
                }
                orderData.getOnSiteQueue().peek().setSkipped(orderData.getOnSiteQueue().peek().getIfSkipped() + 1); //increment the moved orders skips by one
            }
        }
        else if (orderData.getNextOrderObject().getOrderType() == 3 && orderData.getNextOrderObject().getIfSkipped() < 3){//if the next order is an P Object and has been skipped less than 3 times
            if(!orderData.getDriveThroughQueue().isEmpty()){          //if DT queue is not empty
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while(!orderData.getPhoneQueue().isEmpty()){
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while(!temp.isEmpty()){
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1); //increment the moved orders skips by one
            }
            else if(!orderData.getOnSiteQueue().isEmpty()){          //if O queue is not empty
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while(!orderData.getPhoneQueue().isEmpty()){
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while(!temp.isEmpty()){
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1); //increment the moved orders skips by one
            }

        }
        else if (orderData.getNextOrderObject().getOrderType() == 4 && orderData.getNextOrderObject().getIfSkipped() < 3) {//if the next order is an DD Object and has been skipped less than 3 times
            if (!orderData.getDriveThroughQueue().isEmpty()) {      //if the DT queue is not empty
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getDoorDashQueue().isEmpty()) {
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while (!temp.isEmpty()) {
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1); //increment the moved orders skips by one
            } else if (!orderData.getOnSiteQueue().isEmpty()) {     //if the O queue is not empty
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getDoorDashQueue().isEmpty()) {
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while (!temp.isEmpty()) {
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1); //increment the moved orders skips by one
            } else if (!orderData.getPhoneQueue().isEmpty()) {      // if the P queue is not empty
                temp.add(orderData.getNextOrderObject());
                orderData.setNextOrderObject(null);
                orderData.setNextOrder("");
                while (!orderData.getDoorDashQueue().isEmpty()) {
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while (!temp.isEmpty()) {
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1); //increment the moved orders skips by one
            }
        }
    }

    /**
     * Makes sure next and current order have an object
     * Makes sure the next order is the highest priority order
     * If not, puts next order back into respective queue and sets next order
     */
    public void checkQueue(){
        if(orderData.getNextOrder().equals("")){   //checks for a next order object, if none set the next order
            setNextOrder();
            return;
        }
        if(orderData.getCurrentOrder().equals("")){ //checks for a current order object, if none set the current order
            setCurrentOrder();
            return;
        }

        replaceNextOrder();

        if(orderData.getNextOrder().equals("")) {   //sets the next order if it is still empty
            setNextOrder();
        }
    }


    /**
     * Removes the requested order from OrderData unless its complete or in progress
     *
     * @param order
     */
    public void cancelOrder(Order order){
        if(orderData.getNextOrderObject().getOrderNumber() == order.getOrderNumber()){
            orderData.setNextOrder("");
            orderData.setNextOrderObject(null);
            setNextOrder();     //cancels the current order if requested
        }
        else if(orderData.getDriveThroughQueue().contains(order)){
            orderData.getDriveThroughQueue().remove(order); //removes order from DT queue if requested
        }
        else if(orderData.getOnSiteQueue().contains(order)){
            orderData.getOnSiteQueue().remove(order);//removes order from O queue if requested
        }
        else if(orderData.getPhoneQueue().contains(order)){
            orderData.getPhoneQueue().remove(order);//removes order from P queue if requested
        }
        else if(orderData.getDoorDashQueue().contains(order)){
            orderData.getDoorDashQueue().remove(order);//removes order from DD queue if requested
        }
    }

    /**
     * Returns true if all four queues are empty, false otherwise
     *
     * @return boolean
     */
    public boolean ifEmpty(){
        if(orderData.getDriveThroughQueue().isEmpty() && orderData.getOnSiteQueue().isEmpty()
                && orderData.getPhoneQueue().isEmpty() && orderData.getDoorDashQueue().isEmpty()) {
            return true;
        }
        return false;
    }
}
