/**
 * PhoneNumberException.java
 *
 * Throws a phone number exception
 *
 * @author William Freeburn
 */
package Exceptions;


public class PhoneNumberException extends Exception{
    public PhoneNumberException(){
        super();
        System.out.println("Do not use letters in the phone number");
    }
}
