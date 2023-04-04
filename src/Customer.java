import java.util.Scanner;


/*
Where would the cancel method be implemented?

How to implement the cost?

How to implement the "mark" placed on an item ready for pickup
 */
public class Customer {
    int index = 0;
    public void placeOrder(){
        int item = 0, quantity = 0, method = 0, cost = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What will you be placing your order for:");
        System.out.println("1.Drivethrough 2.Onsite 3.Pickup 4.Doordash");
        while (method < 1 || method > 4) {
            System.out.println("Please enter a number 1-4");
            method = scanner.nextInt();
        }
        System.out.println("Please choose an item from the following: ");
        System.out.println("1.Burger($10) 2.Fries($5) 3.Hotdog($2)");
        while (item < 1 || item > 3) {
            System.out.println("Please enter a number 1-3");
            item = scanner.nextInt();
        }
        System.out.println("How many?");
        while (quantity < 1) {
            System.out.println("Please enter a number greater than one");
            quantity = scanner.nextInt();
        }

        if(method == 1) {
            Drivethrough order = new Drivethrough(item, quantity, method, index);}
        else if (method == 2) {
            Onsite order = new Onsite(item, quantity, method, index);}
        else if (method == 3){
            Pickup order = new Pickup(item, quantity, method, index);}
        else{
            Doordash order = new Doordash(item, quantity, method, index);}
        index += 1;
    }
    public static void main(String[] args) {
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        while(i!=3) {
            System.out.println("Would you like to place(1) an order, cancel(2) an order, or quit(3) the program");
        }
    }
}
