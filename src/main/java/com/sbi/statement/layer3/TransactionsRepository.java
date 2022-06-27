package com.sbi.statement.layer3;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;

@Repository
public interface TransactionsRepository extends CrudRepository <Transactions, Integer>{
	
	public List<Transactions> findByTransTimeBetweenAndAcct(LocalDate date1, LocalDate date2, Accounts account);
	
	@Query(nativeQuery = true, value="select t.trans_id from Transactions T where t.acct_no = :acct_no and t.trans_time between :startDate and :endDate")
	public List<Transactions> getCustomStatementRepo(@Param("acct_no") int acct_no, @Param("startDate") LocalDate date, @Param("endDate") LocalDate date2);

}
