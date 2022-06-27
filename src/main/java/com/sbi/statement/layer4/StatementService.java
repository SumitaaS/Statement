package com.sbi.statement.layer4;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;

@Service
public interface StatementService {
	
	public List<Transactions> getMonthlyStatement(int accNo,int month,int year);
	
	public List<Transactions> getYearlyStatement(int accNo,int year);
	
//	@Query(nativeQuery = true, value="select t.trans_id from Transactions T where t.acct_no = :acct_no and t.trans_time between :startDate and :endDate")
//	public List<Transactions> getCustomStatement(@Param("acct_no") int acct_no, @Param("startDate") LocalDate date, @Param("endDate") LocalDate date2);

	public List<Transactions> getCustomStatement(LocalDate ld1,LocalDate ld2,int a);
}
