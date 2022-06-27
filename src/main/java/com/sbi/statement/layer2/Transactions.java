package com.sbi.statement.layer2;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Transactions")
public class Transactions {
	
	@Id
	@GeneratedValue
	@Column(name="trans_id")
	private int transId;
	@Column(name="trans_time")
	private LocalDate transTime;
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
	@JsonBackReference
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
	public LocalDate getTransTime() {
		return transTime;
	}
	public void setTransTime(LocalDate d) {
		this.transTime = d;
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
	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", transTime=" + transTime + ", transType=" + transType
				+ ", transAmount=" + transAmount + ", refAccount=" + refAccount + ", remainingBalance="
				+ remainingBalance + "]";
	}
	
	

	
}
