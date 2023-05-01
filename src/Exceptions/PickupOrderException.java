/**
 * PickupOrderException.java
 *
 * Throws a pickup exception
 *
 * @author William Freeburn
 */
package Exceptions;

public class PickupOrderException extends Exception{
    public PickupOrderException(){
        super();
        System.out.println( "No pickup orders or no order selected" );
    }
}
