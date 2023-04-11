package Controller;

import Model.Order;
import Model.OrderData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterController extends FileWriter {
    public static File file = new File("savedData");
    public FileWriter fileWriter = new FileWriter("savedData");

    public FileWriterController() throws IOException {
        super(file);
    }


    public void writeToFile(Order order) throws IOException {
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

        fileWriter.write("HEADER");
        fileWriter.write(order.getOrderType()); //order type
        fileWriter.write(String.valueOf(order.getItemID()));
        fileWriter.write(String.valueOf(order.getQuantities()));
        fileWriter.write(order.getOrderNumber());
        fileWriter.write(order.getName());
        fileWriter.write(order.getIfSkipped());
        if(order.getIsComplete()) {fileWriter.write("Completed");}
    }

    public static void main(String[] args) throws IOException {
        FileWriterController f = new FileWriterController();
        OrderData orderData = new OrderData();
        Order order = new Order("Order1 DD", 1, 4);
        Order order1 = new Order("Order2 DT",2,2);
        Order order2 = new Order("Order3 OS",3,1);
        orderData.addOrder(order);
        orderData.addOrder(order1);
        orderData.addOrder(order2);
        f.writeToFile(order);
        f.writeToFile(order1);
        f.writeToFile(order2);


    }
}


