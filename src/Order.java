import java.lang.String;
public abstract class Order {
    protected int item, quantity, method, index, skips = 0;
    protected boolean complete = false;
    public Order(int item, int quantity, int method, int index){
        this.item = item;
        this.quantity = quantity;
        this.method = method;
        this.index = index;
    }

    public void sendtoQueue(){

    };



}
