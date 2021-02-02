package cff.cashflowforecast.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PrivateStatusException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	public PrivateStatusException(String message){
    	super(message);
    }

}
