package com.sbi.statement.layer2;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="Transactions")
public class Transactions {
	
	@Id
	@GeneratedValue
	@Column(name="trans_id")
	private int transId;
	@Column(name="trans_time")
	private Date transTime;
	@Column(name="trans_type")
	private char transType;
	@Column(name="trans_amount")
	private double transAmount;
	@Column(name="ref_account")
	private String refAccount;
	@Column(name="remaining_balance")
	private double remainingBalance;
	
	@ManyToOne
	@JoinColumn(name="accNo")
	private Accounts acct;
	
	
	public Accounts getAcct() {
		return acct;
	}
	public void setAcct(Accounts acct) {
		this.acct = acct;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public char getTransType() {
		return transType;
	}
	public void setTransType(char transType) {
		this.transType = transType;
	}
	public double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	public String getRefAccount() {
		return refAccount;
	}
	public void setRefAccount(String refAccount) {
		this.refAccount = refAccount;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	
}
