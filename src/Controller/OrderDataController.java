package Controller;
import Model.HashTableID;
import Model.Order;
import Model.OrderData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OrderDataController {
    OrderData orderData = new OrderData();
     public OrderDataController(){}

    /**
     * sets the current order to the next order and calls function to replace next order
     */
    public void setCurrentOrder(){
        orderData.setCurrentOrderObject(orderData.getNextOrderObject());
        orderData.setCurrentOrder(orderData.getNextOrder());
        setNextOrder();
     }

    /**
     * Sets nextOrder to its appropriate value (only use when NULL, call checkQueue otherwise)
     */
    public void setNextOrder(){
        HashTableID hashTable = new HashTableID();
        String input = "";
        ArrayList<Integer> itemID = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        if(!orderData.getDriveThroughQueue().isEmpty()){        //if the DT queue is empty
            input += orderData.getDriveThroughQueue().peek().getName() + "\n";
            itemID = orderData.getDriveThroughQueue().peek().getItemID();
            quantities = orderData.getDriveThroughQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){      //read nessesary information into input string and assign to nextOrder
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getDriveThroughQueue().remove());    //removes the object from queue and assigns it to next order object
        }
        else if(!orderData.getOnSiteQueue().isEmpty()){
            input += orderData.getOnSiteQueue().peek().getName() + "\n";
            itemID = orderData.getOnSiteQueue().peek().getItemID();
            quantities = orderData.getOnSiteQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());
        }
        else if(!orderData.getPhoneQueue().isEmpty()){
            input += orderData.getPhoneQueue().peek().getName() + "\n";
            itemID = orderData.getPhoneQueue().peek().getItemID();
            quantities = orderData.getPhoneQueue().peek().getQuantities();
            for(int i = 0;i < itemID.size(); i++){
                input += quantities.get(i) + " " ;
                input += hashTable.getItemIDName(itemID.get(i)) + "\n";
            }
            orderData.setNextOrderObject(orderData.getPhoneQueue().remove());
        }
        else if(!orderData.getDoorDashQueue().isEmpty()){
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
     }

    /**
     * Checks that the next order up is the correct one, if not order is placed back into its queue
     *
     */
    public void checkQueue(){
        Queue<Order> temp = new LinkedList<>();
        if(orderData.getNextOrderObject().getOrderType() == 2 && orderData.getNextOrderObject().getIfSkipped() < 3){            //if the next order is an O Object
            if(!orderData.getDriveThroughQueue().isEmpty()) {               //if the DT Queue has an object
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getOnSiteQueue().isEmpty()){
                    temp.add(orderData.getOnSiteQueue().remove());         //puts the next order back into O queue
                }
                while(!temp.isEmpty()){
                    orderData.setOnSiteQueue(temp.remove());
                }
                orderData.getOnSiteQueue().peek().setSkipped(orderData.getOnSiteQueue().peek().getIfSkipped() + 1);
            }
        }
        else if (orderData.getNextOrderObject().getOrderType() == 3 && orderData.getNextOrderObject().getIfSkipped() < 3){       //if the next order is a P Object
            if(!orderData.getDriveThroughQueue().isEmpty()){                //if DT queue has an object
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getPhoneQueue().isEmpty()){
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while(!temp.isEmpty()){
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1);
            }
            else if(!orderData.getOnSiteQueue().isEmpty()){              //if O queue has an object
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getPhoneQueue().isEmpty()){
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while(!temp.isEmpty()){
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.getPhoneQueue().peek().setSkipped(orderData.getPhoneQueue().peek().getIfSkipped() + 1);
            }

        }
        else if (orderData.getNextOrderObject().getOrderType() == 4 && orderData.getNextOrderObject().getIfSkipped() < 3){     //if the next order is a DD object
            if(!orderData.getDriveThroughQueue().isEmpty()){
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getDoorDashQueue().isEmpty()){
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while(!temp.isEmpty()){
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);
            }
            else if(!orderData.getOnSiteQueue().isEmpty() ){
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getDoorDashQueue().isEmpty()){
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while(!temp.isEmpty()){
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);
            }
            else if(!orderData.getPhoneQueue().isEmpty()){
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getDoorDashQueue().isEmpty()){
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while(!temp.isEmpty()){
                    orderData.setDoorDashQueue(temp.remove());
                }
                orderData.getDoorDashQueue().peek().setSkipped(orderData.getDoorDashQueue().peek().getIfSkipped() + 1);
            }
            if(orderData.getNextOrderObject() == null)
                setNextOrder();
        }
     }

    /**
     * Removes the requested order from OrderData
     *
     * @param order
     */
    public void remove(Order order){
        if(orderData.getNextOrderObject() == order){
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
        else{
            //the customer requested to cancel an order that doesnt exist
        }
    }
}
