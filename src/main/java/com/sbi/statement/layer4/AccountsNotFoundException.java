package com.sbi.statement.layer4;

public class AccountsNotFoundException extends RuntimeException {
	
	public AccountsNotFoundException(String msg)
	{
		super(msg);
	}

}
