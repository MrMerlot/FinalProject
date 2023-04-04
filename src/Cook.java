import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cook {
    private Queue<Order> orderQueue = new LinkedList<>();
    private ArrayList<Order> pickup = new ArrayList<>();
    public Cook(){

    }
    public void getOrder(Order order){
        orderQueue.add(order);                                       //implement higher priority skips
        //if(orderQueue.poll().method > order.method)
        //    orderQueue.add();
    }
    public void completeOrder(){                                //Rubric says to mark orders ready for pickup
        orderQueue.peek().complete = true;
        if(orderQueue.peek().method == 3)
            pickup.add(orderQueue.peek());
        orderQueue.remove();
    }
    public void pickedUp(Order order){
        pickup.remove(order);
    }

    public void cancelOrder(Order order){
        orderQueue.remove(order);
    }

    //getters                                                   //get cost could be the only implementation of cost

    //Daniel Edinger
}
