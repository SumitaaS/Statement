package com.sbi.statement.layer4;

public class TransactionsNotFoundException extends RuntimeException {
	
	public TransactionsNotFoundException (String msg)
	{
		super(msg);
	}

}
