package com.sbi.statement.layer4;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer3.AccountsRepository;
import com.sbi.statement.layer3.TransactionsRepository;

@Service
public class StatementServiceImplementation implements StatementService {

	@Autowired
	AccountsRepository acctRepo;

	@Autowired
	TransactionsRepository tranRepo;

	
	@Override
	public List<Transactions> getCustomStatementEmail(LocalDate date1, LocalDate date2, String email) {
		
		
		Accounts a = new Accounts();
		a.setEmail(email);
		a = acctRepo.findByEmail(email);

		List<Transactions> tl = tranRepo.findByTransTimeBetweenAndAcct(date1, date2, a);
		System.out.println("\t1.size" + tl.size());
		return tl;
	}



}
