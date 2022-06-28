package com.sbi.statement.layer4;

public class IncorrectPasswordException extends RuntimeException {
	
	public IncorrectPasswordException(String msg)
	{
		super(msg);
	}

}
