package com.david.florczak.westmarches.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserInvalidEmailException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

	public UserInvalidEmailException(String message) {
        super(message);
    }
	
}
