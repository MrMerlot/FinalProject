package Controller;

import Model.Order;
import Model.OrderData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileWriterController extends FileWriter {
    public static File file = new File("savedData.txt");
    //Creates a static file to save the orders in

    public FileWriterController() throws IOException {
        super(file);
    }
    //constructor

    public void writeToFile(Order order,FileWriterController fileWriter) throws IOException {
        /**
         * -HEADER-
         * ORDER TYPE
         * [ItemID, ItemID, ItemID]
         * [Quantity, Quantity, Quantity]
         * ORDER NUMBER
         * CUSTOMER NAME
         * TIMES SKIPPED
         * IS COMPLETED
         **/
        //Order storage template
        try {
          //  fileWriter.write("HEADER"+String.format("%n"));
            fileWriter.write(order.getName()+String.format("%n"));
            //Writes in order name and then ends the line
            fileWriter.write(order.getOrderNumber()+String.format("%n"));
            //Writes in order number and then ends the line
        fileWriter.write(order.getOrderType()+String.format("%n"));
            //Writes in order type and then ends the line
        fileWriter.write(String.valueOf(order.getItemID())+String.format("%n"));
            //Writes in order items as an array and then ends the line
        fileWriter.write(String.valueOf(order.getQuantities())+String.format("%n"));
            //Writes in order quantities as an array and then ends the line
        fileWriter.write(order.getIfSkipped()+String.format("%n"));
            //Writes in order times skipped and then ends the line
            fileWriter.write((order.getIsComplete()+String.format("%n")));
            //Writes in order completion status and then ends the line
        }
        catch(IOException e){
            e.getStackTrace();
            //Catches exception
        }
    }
    public List<Integer> toIntArray(String x){ //Converts the string line to int array
        x=x.replace("[",""); //Removes initial bracket for the input string
        x=x.replace("]",""); //Removes final bracket for the input string
        x=x.replace(" ",""); //Removes all spaces from the input string
        String[] y = x.split(","); //Splits the string into a string array at commas
        List<Integer> array = new ArrayList<>();
        for(int i=0;i<y.length;i++){
            array.add(i,Integer.parseInt(y[i])); //Converts string array to integer list
        }
        return array;
    }
    public void readInFile(File file, OrderData o) throws FileNotFoundException {
        Scanner s = new Scanner(file); //Read in from file
        while(s.hasNextLine()) {
            Order order = new Order(s.nextLine(), //Creates an order object based on the first 3 lines of
                    Integer.parseInt(s.nextLine()), // the file read in
                    Integer.parseInt(s.nextLine())); //name, number, type
            order.setItemID(toIntArray(s.nextLine())); //adds ItemID's to order
            order.setQuantities(toIntArray(s.nextLine())); //adds Quantities to order
            order.setSkipped(Integer.parseInt(s.nextLine())); //sets skipped count to order
            order.setComplete(s.nextLine());//sets the order completion status
            o.addOrder(order); //adds order to static order data
        }
    }

    public static void main(String[] args) throws IOException {
        //Creates test case
        FileWriterController f = new FileWriterController();
        OrderData orderData = new OrderData();
        Order order = new Order("Order1 DD", 1, 4);
        Order order1 = new Order("Order2 DT",2,2);
        Order order2 = new Order("Order3 OS",3,1);
        order.addItem(2,3);
        order.completeOrder();
        order.addItem(6,7);
        order1.addItem(3,2);
        order2.addItem(1,1);
        orderData.addOrder(order);
        orderData.addOrder(order1);
        orderData.addOrder(order2);

        try {
            f.writeToFile(order, f);
            f.writeToFile(order1,f);
            f.writeToFile(order2,f);
            f.close();

            f.readInFile(file,orderData);
            Queue<Order> x = orderData.getPhoneQueue();
            for(int i=0;i<x.size();i++){
                System.out.println(x.poll().getName());
            }
        }
        catch (IOException e) {
            System.out.println("Exception");
            e.getStackTrace();
        }
        System.out.println("done");

    }
}


