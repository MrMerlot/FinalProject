package Exceptions;

public class CancelException extends Exception{
    public CancelException(){
        super();
        System.out.println("Order doesnt exist or is already in progress, cannot cancel");
    }
}
