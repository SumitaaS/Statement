package com.sbi.statement.layer4;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbi.statement.layer2.Transactions;

@Service
public interface StatementService {
	
	public List<Transactions> getMonthlyStatement(int month,int year);
	
	public List<Transactions> getYearlyStatement(int year);
	
	public List<Transactions> getCustomStatement(Date fromDate,Date toDate);

}
