package Controller;

import Model.Order;
import Model.OrderData;

import java.util.LinkedList;
import java.util.Queue;

public class OrderDataController {
    OrderData orderData;
    int
     public OrderDataController(OrderData orderData){
         this.orderData = orderData;
     }

    /**
     * sets the current order to the next order and calls funtionn to replace next order
     */
    public void finishCurrentOrder(){
        //send the current order if needed
        orderData.setCurrentOrderObject(orderData.getNextOrderObject());
        setNextOrder();
     }

    /**
     * assigns nextOrder its appropriate value
     */
    public void setNextOrder(){
        if(!orderData.getDriveThroughQueue().isEmpty()){
            orderData.setNextOrderObject(orderData.getDriveThroughQueue().remove());
        }
        else if(!orderData.getOnSiteQueue().isEmpty()){
            orderData.setNextOrderObject(orderData.getOnSiteQueue().remove());
        }
        else if(!orderData.getPhoneQueue().isEmpty()){
            orderData.setNextOrderObject(orderData.getPhoneQueue().remove());
        }
        else{
            orderData.setNextOrderObject(orderData.getDoorDashQueue().remove());
        }
     }
     public void checkQueue(){
        Queue<Order> temp = new LinkedList<>();
        if(orderData.getNextOrderObject().getOrderType() == 2 ){            //if the next order is an O Object
            if(!orderData.getDriveThroughQueue().isEmpty()) {               //if the DT Queue has an object
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getOnSiteQueue().isEmpty()){
                    temp.add(orderData.getOnSiteQueue().remove());         //puts the next order back into O queue
                }
                while(!temp.isEmpty()){
                    orderData.setOnSiteQueue(temp.remove());
                }
            }
        }
        else if (orderData.getNextOrderObject().getOrderType() == 3){       //if the next order is a P Object
            if(!orderData.getDriveThroughQueue().isEmpty()){                //if DT queue has an object
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getPickupOrders().isEmpty()){
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while(!temp.isEmpty()){
                    orderData.setPhoneQueue(temp.remove());
                }
                orderData.setNextOrder();

            }
            else if(!orderData.getOnSiteQueue().isEmpty()){              //if O queue has an object
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getPhoneQueue().isEmpty()){
                    temp.add(orderData.getPhoneQueue().remove());    //puts the next order back into P queue
                }
                while(!temp.isEmpty()){
                    orderData.setPhoneQueue(temp.remove());
                }
            }

        }
        else if (orderData.getNextOrderObject().getOrderType() == 4){     //if the next order is a DD object
            if(!orderData.getDriveThroughQueue().isEmpty()){
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getDoorDashQueue().isEmpty()){
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while(!temp.isEmpty()){
                    orderData.setDoorDashQueue(temp.remove());
                }
            }
            else if(!orderData.getOnSiteQueue().isEmpty() ){
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getDoorDashQueue().isEmpty()){
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while(!temp.isEmpty()){
                    orderData.setDoorDashQueue(temp.remove());
                }
            }
            else if(!orderData.getPhoneQueue().isEmpty()){
                temp.add(orderData.getNextOrderObject());
                while(!orderData.getDoorDashQueue().isEmpty()){
                    temp.add(orderData.getDoorDashQueue().remove());    //puts the next order back into DD queue
                }
                while(!temp.isEmpty()){
                    orderData.setDoorDashQueue(temp.remove());
                }
            }

        }
     }
     //if empty, fill with information (start of code)

     //set variables to correct values
    //clears variables at end of runtime
    public void clear(){
         orderData.setCurrentOrderObject(null);
         orderData.setNextOrderObject(null);
     }
     //removed
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
