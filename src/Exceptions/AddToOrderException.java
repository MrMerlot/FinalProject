/**
 * AddToOrderException.java
 *
 * Throws a submit order exception
 *
 * @author William Freeburn
 */
package Exceptions;


public class AddToOrderException extends Exception{
    public AddToOrderException(){
        super();
        System.out.println("Invalid selection: Cannot add to order");
    }
}
