package Controller;

import Model.Order;
import Model.OrderData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileWriterController extends FileWriter {
    public static File file = new File("savedData.txt");

    public FileWriterController() throws IOException {
        super(file);
    }

    public void writeToFile(Order order,FileWriterController fileWriter) throws IOException {
        /**
        // * HEADER
         * ORDER TYPE
         * [ItemID, ItemID, ItemID]
         * [Quantity, Quantity, Quantity]
         * ORDER NUMBER
         * CUSTOMER NAME
         * TIMES SKIPPED
         * IS COMPLETED
         **/
        try {
          //  fileWriter.write("HEADER"+String.format("%n"));
            fileWriter.write(order.getName()+String.format("%n"));
            fileWriter.write(order.getOrderNumber()+String.format("%n"));
        fileWriter.write(order.getOrderType()+String.format("%n")); //order type
        fileWriter.write(String.valueOf(order.getItemID())+String.format("%n"));
        fileWriter.write(String.valueOf(order.getQuantities())+String.format("%n"));
        fileWriter.write(order.getIfSkipped()+String.format("%n"));
        if(order.getIsComplete()) {fileWriter.write("Completed"+String.format("%n"));}
        else{fileWriter.write("Incomplete"+String.format("%n"));}
        }
        catch(IOException e){
            e.getStackTrace();
            System.out.println("Exception");
        }
    }
    public List<Integer> toIntArray(String[] x){
        List<Integer> array = new ArrayList<>();
        for(int i=0;i<x.length;i++){
            array.set(i, Integer.parseInt(x[i]));
        }
        return array;
    }
    public Order readInFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        String temp;
        Order order = new Order(s.nextLine(), Integer.parseInt(s.nextLine()),
                Integer.parseInt(s.nextLine()));
        temp = s.nextLine();
        temp=temp.replace("[","");
        temp=temp.replace("]","");
        temp=temp.replace(" ","");
        order.setItemID(toIntArray(temp.split(",")));
        temp = s.nextLine();
        temp=temp.replace("[","");
        temp=temp.replace("]","");
        temp=temp.replace(" ","");
        order.setQuantities(toIntArray(temp.split(",")));
        order.setSkipped(Integer.parseInt(s.nextLine()));
        order.setComplete(s.nextLine());
        return order;
    }

    public static void main(String[] args) throws IOException {
        FileWriterController f = new FileWriterController();
        OrderData orderData = new OrderData();
        OrderData o = new OrderData();
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

        String x = "[2, 3, 5]";
        x=x.replace("[","");
        x=x.replace("]","");
        x=x.replace(" ","");
        String[] y = x.split(",");
        int[] a = new int[6];
        for(int i=0;i< y.length;i++){
            a[i] = Integer.parseInt(y[i]);
            System.out.println(a[i]);
        }
        try {
            f.writeToFile(order, f);
            f.writeToFile(order1,f);
            f.writeToFile(order2,f);
            f.close();

            o.addOrder(f.readInFile(file));
            //o.addOrder(f.readInFile(file));
            //o.addOrder(f.readInFile(file));
            //System.out.println(o);
        }
        catch (IOException e) {
            System.out.println("Exception");
            e.getStackTrace();
        }
        System.out.println("done");

//Commit
    }
}


