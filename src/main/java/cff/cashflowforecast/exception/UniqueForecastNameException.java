package cff.cashflowforecast.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UniqueForecastNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public UniqueForecastNameException(String message){
    	super(message);
    }


}
