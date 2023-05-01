/**
 * FinishOrderException.java
 *
 * Throws a finish order exception
 *
 * @author William Freeburn
 */
package Exceptions;

public class FinishOrderException extends Exception{

    public FinishOrderException(){
        super();
        System.out.println( "There are no orders that can be finished" );
    }
}


