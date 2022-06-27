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
	
		public List<Transactions> getCustomStatementEmail(LocalDate ld1,LocalDate ld2,String e);
}
