/**
 * CustomerNameException.java
 *
 * Throws a customer name exception
 *
 * @author William Freeburn
 */
package Exceptions;

public class CustomerNameException extends Exception{
    public CustomerNameException(){
        super();
        System.out.println("Enter a name before submitting your order");
    }
}
