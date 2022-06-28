package com.sbi.statement.layer4;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(AccountsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> accountsNotFoundHandler(AccountsNotFoundException ex)
	{
		System.out.println("inside accounts not found handler.....");
		Map<String,String> errors = new HashMap<String,String>();
		errors.put("message", ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(TransactionsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> transactionsNotFoundHandler(TransactionsNotFoundException ex)
	{
		System.out.println("inside transaction not found handler.....");
		Map<String,String> errors = new HashMap<String,String>();
		errors.put("message", ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(IncorrectPasswordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> incorrectPasswordHandler(IncorrectPasswordException ex)
	{
		System.out.println("inside incorrect password.....");
		Map<String,String> errors = new HashMap<String,String>();
		errors.put("message", ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

}
