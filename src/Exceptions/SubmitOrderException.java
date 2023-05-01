/**
 * SubmitOrderException.java
 *
 * Throws a submit order exception
 *
 * @author William Freeburn
 */
package Exceptions;

public class SubmitOrderException extends Exception{
    public SubmitOrderException(){
        super();
        System.out.println("Cannot submit empty orders");
    }

}
