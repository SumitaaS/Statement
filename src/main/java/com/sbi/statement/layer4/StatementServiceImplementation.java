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
	public List<Transactions> getMonthlyStatement(int acct_no, int month, int year) {
		Accounts a = new Accounts();
		a.setAccountNumber(acct_no);
		LocalDate startDate = LocalDate.of(year, month, 01);
		LocalDate endDate = startDate.withDayOfMonth(startDate.getMonth().length(startDate.isLeapYear()));

		List<Transactions> tl = tranRepo.findByTransTimeBetweenAndAcct(startDate, endDate, a);
		System.out.println("\t1.size" + tl.size());
		return tl;
	}

	@Override
	public List<Transactions> getYearlyStatement(int accNo, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getCustomStatement(LocalDate date1, LocalDate date2, int acct_no) {
		Accounts a = new Accounts();
		a.setAccountNumber(acct_no);

		List<Transactions> tl = tranRepo.findByTransTimeBetweenAndAcct(date1, date2, a);
		System.out.println("\t1.size" + tl.size());
		return tl;
	}

}
