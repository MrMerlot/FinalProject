package Controller;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class FinishOrderException extends Exception{

    public FinishOrderException(){
        super( "There are no orders that can be finished." );
    }
}


