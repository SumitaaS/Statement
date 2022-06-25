package com.sbi.statement.layer2;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Accounts")
public class Accounts {
	
	@Id
	@Column(name="account_number")
	private int accountNumber;
	@Column(name="email")
	private String email;
	@Column(name="account_holder_name")
	private String accountHolderName;
	@Column(name="account_holder_address")
	private String accountHolderAddress;
	@Column(name="account_balance")
	private double currentBalance;
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy="acct", cascade=CascadeType.ALL)
	private List<Transactions> trans;
	
	
	public List<Transactions> getTrans() {
		return trans;
	}
	public void setTrans(List<Transactions> trans) {
		this.trans = trans;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAccountHolderAddress() {
		return accountHolderAddress;
	}
	public void setAccountHolderAddress(String accountHolderAddress) {
		this.accountHolderAddress = accountHolderAddress;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}