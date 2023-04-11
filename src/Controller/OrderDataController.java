package Controller;

import Model.Order;
import Model.OrderData;

import java.util.LinkedList;
import java.util.Queue;

public class OrderDataController {
    OrderData orderData = new OrderData();
     public OrderDataController(){}

    /**
     * sets the current order to the next order and calls function to replace next order
     */
    public void setCurrentOrder(){
        //send the current order if needed
        orderData.setCurrentOrderObject(orderData.getNextOrderObject());
        setNextOrder();
     }

    /**
     * Sets nextOrder to its appropriate value (only use when NULL, call checkQueue otherwise)
     */
    public void setNextOrder(){
        String input = "";
        if(!orderData.getDriveThroughQueue().isEmpty()){
            input += orderData.getDriveThroughQueue().peek().getOrderNumber() + "\n";
            input += orderData.getDriveThroughQueue().peek().getItemID() + " ";
            input += orderData.getDriveThroughQueue().peek().getQuantities() + "\n";
            orderData.setNextOrder(input);
            orderData.setNextOrderObject(orderData.getDriveThroughQueue().remove());
        }
        else if(!orderData.getOnSiteQueue().isEmpty()){
            input += orderData.getOnSiteQueue().peek().getOrderNumber() + "\n";
            input += orderData.getOnSiteQueue().peek().getItemID() + " ";
            input += orderData.getOnSiteQueue().peek().getQuantities() + "\n";
            orderData.setNextOrder(input);
            orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());
        }
        else if(!orderData.getPhoneQueue().isEmpty()){
            input += orderData.getPhoneQueue().peek().getOrderNumber() + "\n";
            input += orderData.getPhoneQueue().peek().getItemID() + " ";
            input += orderData.getPhoneQueue().peek().getQuantities() + "\n";
            orderData.setNextOrder(input);
            orderData.setNextOrderObject(orderData.getPhoneQueue().remove());
        }
        else if(!orderData.getDoorDashQueue().isEmpty()){
            input += orderData.getDoorDashQueue().peek().getOrderNumber() + "\n";
            input += orderData.getDoorDashQueue().peek().getItemID() + " ";
            input += orderData.getDoorDashQueue().peek().getQuantities() + "\n";
            orderData.setNextOrder(input);
            orderData.setNextOrderObject(orderData.getDoorDashQueue().remove());
        }
     }

    /**
     * Checks that the next order up is the correct one, if not order is placed back into queue
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
                while(!orderData.getPickupOrders().isEmpty()){
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
