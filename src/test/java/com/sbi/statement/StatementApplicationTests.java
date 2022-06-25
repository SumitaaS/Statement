package com.sbi.statement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer3.AccountsRepository;

@SpringBootTest
class StatementApplicationTests {

	@Autowired
	AccountsRepository stmtrepo;
	
	@Test
	void contextLoads() {
		
		Accounts a = new Accounts();
		a.setAccountHolderAddress("NaviMumbai");
		a.setAccountHolderName("Sumitaa");
		a.setAccountNumber(222222222);
		a.setCurrentBalance(100000);
		a.setEmail("sumitaa@email.com");
		a.setPassword("987654321");
		
		Transactions t1 = new Transactions();
		t1.setRefAccount("987654321");
		t1.setRemainingBalance(95000.00);
		t1.setTransAmount(5000);
		Date d =new Date(2022,03,25);
		t1.setTransTime(d);
		t1.setTransType('D');
		t1.setAcct(a);
		
		
		Transactions t2 = new Transactions();
		t2.setRefAccount("987654321");
		t2.setRemainingBalance(90000.00);
		t2.setTransAmount(5000);
		Date d1 =new Date(2022,03,25);
		t2.setTransTime(d1);
		t2.setTransType('D');
		t2.setAcct(a);
		
		List<Transactions> tl = new ArrayList<Transactions>();
		tl.add(t1);
		tl.add(t2);
		a.setTrans(tl);
		
		stmtrepo.save(a);
		
	}

}
