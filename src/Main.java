import java.util.*;

class Order {
    private String item;
    private String method;
    private int priority;
    private int skipCount;

    public Order(String item, String method) {
        this.item = item;
        this.method = method;
        if (method.equals("Drive-through")) {
            this.priority = 3;
        } else if (method.equals("Onsite")) {
            this.priority = 2;
        } else if (method.equals("Phone")) {
            this.priority = 1;
        } else {
            this.priority = 0;
        }
        this.skipCount = 0;
    }

    public String getItem() {
        return item;
    }

    public String getMethod() {
        return method;
    }

    public int getPriority() {
        return priority;
    }

    public void incrementSkipCount() {
        skipCount++;
    }

    public int getSkipCount() {
        return skipCount;
    }
}

class RestaurantOrderingSystem {
    private PriorityQueue<Order> driveThroughOrders;
    private PriorityQueue<Order> onsiteOrders;
    private PriorityQueue<Order> phoneOrders;
    private PriorityQueue<Order> doorDashOrders;

    public RestaurantOrderingSystem() {
        driveThroughOrders = new PriorityQueue<>(Comparator.comparingInt(Order::getPriority).reversed());
        onsiteOrders = new PriorityQueue<>(Comparator.comparingInt(Order::getPriority).reversed());
        phoneOrders = new PriorityQueue<>(Comparator.comparingInt(Order::getPriority).reversed());
        doorDashOrders = new PriorityQueue<>(Comparator.comparingInt(Order::getPriority).reversed());
    }

    public void addOrder(Order order) {
        if (order.getMethod().equals("Drive-through")) {
            driveThroughOrders.add(order);
        } else if (order.getMethod().equals("Onsite")) {
            onsiteOrders.add(order);
        } else if (order.getMethod().equals("Phone")) {
            phoneOrders.add(order);
        } else {
            doorDashOrders.add(order);
        }
    }

    public void prioritizeOrders() {
        if (!driveThroughOrders.isEmpty()) {
            return;
        } else if (!onsiteOrders.isEmpty()) {
            return;
        } else if (!phoneOrders.isEmpty()) {
            return;
        } else {
            Order nextDoorDashOrder = doorDashOrders.peek();
            if (nextDoorDashOrder != null && nextDoorDashOrder.getSkipCount() < 3) {
                nextDoorDashOrder.incrementSkipCount();
                doorDashOrders.poll();
                doorDashOrders.add(nextDoorDashOrder);
                prioritizeOrders();
            }
        }
    }

    public Order getNextOrder() {
        prioritizeOrders();
        if (!driveThroughOrders.isEmpty()) {
            return driveThroughOrders.poll();
        } else if (!onsiteOrders.isEmpty()) {
            return onsiteOrders.poll();
        } else if (!phoneOrders.isEmpty()) {
            return phoneOrders.poll();
        } else {
            return doorDashOrders.poll();
        }
    }

    public void fulfillOrder(Order order) {
        System.out.println("Fulfilled order: " + order.getItem() + " (" + order.getMethod() + ")");
    }
}

public class Main {
    public static void main(String[] args) {
        RestaurantOrderingSystem system = new RestaurantOrderingSystem();
        system.addOrder(new Order("Burger", "Drive-through"));
        system.addOrder(new Order("Pizza", "Onsite"));
        system.addOrder(new Order("Salad", "Phone"));
        system.addOrder(new Order("Sushi", "DoorDash"));
        system.addOrder(new Order("Tacos", "Drive-through"));
        system.addOrder(new Order("Fries", "Onsite"));

        for (int i = 0; i < 6; i++) {
            Order order = system.getNextOrder();
            system.fulfillOrder(order);
        }
    }
}