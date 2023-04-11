package Controller;

import Model.Order;
import Model.OrderData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterController extends FileWriter {
    public static File file = new File("savedData.txt");

    public FileWriterController() throws IOException {
        super(file);
    }

    public void writeToFile(Order order,FileWriterController fileWriter) throws IOException {
        /**
         * HEADER
         * ORDER TYPE
         * [ItemID, ItemID, ItemID]
         * [Quantity, Quantity, Quantity]
         * ORDER NUMBER
         * CUSTOMER NAME
         * TIMES SKIPPED
         * IS COMPLETED
         **/
        try {
            fileWriter.write("HEADER"+String.format("%n"));
        fileWriter.write(order.getOrderType()+String.format("%n")); //order type
        fileWriter.write(String.valueOf(order.getItemID())+String.format("%n"));
        fileWriter.write(String.valueOf(order.getQuantities())+String.format("%n"));
        fileWriter.write(order.getOrderNumber()+String.format("%n"));
        fileWriter.write(order.getName()+String.format("%n"));
        fileWriter.write(order.getIfSkipped()+String.format("%n"));
        if(order.getIsComplete()) {fileWriter.write("Completed"+String.format("%n"));}
        else{fileWriter.write("Incomplete"+String.format("%n"));}
        }
        catch(IOException e){
            e.getStackTrace();
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) throws IOException {
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
        }
        catch (IOException e) {
            System.out.println("Exception");
            e.getStackTrace();
        }
        System.out.println("done");


    }
}


