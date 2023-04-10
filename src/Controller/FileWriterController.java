package Controller;

import Model.Order;

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
        fileWriter.write(order.getOrderType); //order type
        fileWriter.write(String.valueOf(order.getItemID()));
        fileWriter.write(String.valueOf(quantity));
        fileWriter.write(orderNumber);
        fileWriter.write(name);
        fileWriter.write(skipped);
        if(complete) {fileWriter.write("Completed");}
    }
}

}
