package net.ms.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
@ResponseStatus(value=HttpStatus.CONFLICT)
public class CustomSQLException extends RuntimeException{

	private static final long serialVersionUID = -5114740604670608618L;
	
	private String message;
	
	public CustomSQLException(String message) {
		super(message);
		this.message=message;
	}
	
}
