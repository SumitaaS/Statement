package com.sbi.statement;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer3.AccountsRepository;
import com.sbi.statement.layer3.TransactionsRepository;
import com.sbi.statement.layer4.StatementService;

@SpringBootTest
class StatementApplicationTests {

	@Autowired
	AccountsRepository acctRepo;
	
	@Autowired
	TransactionsRepository tranRepo;
	
	@Test
	void contextLoads() {
		
		Accounts a = new Accounts();
		a.setAccountHolderAddress("NaviMumbai");
		a.setAccountHolderName("Sumitaa");
		a.setAccountNumber("555555555");
		a.setCurrentBalance(100000);
		a.setEmail("sumitaa@email.com");
		a.setPassword("987654321");
		
		Transactions t1 = new Transactions();
		t1.setRefAccount("987654321");
		t1.setRemainingBalance(95000.00);
		t1.setTransAmount(5000);
		LocalDate d = LocalDate.of(2022, 5, 26);
		System.out.println("DAte :"+d.getYear());
		t1.setTransTime(d);
		t1.setTransType('D');
		t1.setAcct(a);
		
		
		Transactions t2 = new Transactions();
		t2.setRefAccount("987654321");
		t2.setRemainingBalance(90000.00);
		t2.setTransAmount(5000);
		LocalDate d1 = LocalDate.of(2019, 5, 26);
		t2.setTransTime(d1);
		t2.setTransType('D');
		t2.setAcct(a);
		
		List<Transactions> tl = new ArrayList<Transactions>();
		tl.add(t1);
		tl.add(t2);
		a.setTrans(tl);
		
		acctRepo.save(a);
		
	}
	
	@Test
	void getAllLoads() {
		
		
		List<Accounts> al = (List<Accounts>) acctRepo.findAll();
		Assertions.assertTrue(al!=null);
		for(Accounts a: al)
		{
			System.out.println("a is"+a);
			System.out.println("---------------------");
//			System.out.println(a.getTrans());
			List<Transactions> tl = a.getTrans();
			Assertions.assertTrue(tl!=null);
			for(Transactions t: tl)
			{
				System.out.println("t is "+t.getTransTime());
			}
		}
		
		
	}
	
	@Test
	void getLoads() {
		
		//for testing
		String s = "2022-05-26";
		Date d = Date.valueOf(s);
		
		List<Transactions> tl2 = new ArrayList<Transactions>();
		Optional<Accounts> a = acctRepo.findById(222222222);
		if(a.isPresent())
		{
			Accounts a1 = a.get();
			List<Transactions> tl = a1.getTrans();
			System.out.println("a is"+a);
			System.out.println("---------------------");
			for(Transactions t: tl)
			{
				System.out.println("Time of transaction "+t.getTransId()+" is "+t.getTransTime());
				System.out.println("the passed date is "+d);
				if(t.getTransTime().equals(d))
				{
					tl2.add(t);
				}
			}
		
			if (tl2!= null)
			{
				for(Transactions t: tl2)
				{
					System.out.println("t is "+t);
				}	
			}	
			else
			{
				System.out.println("nothing fetched");
			}
		}
		else
		{
			System.out.println("account is not present");
		}
	}
	
	@Test
	void getCustomDate() {
		
		//for testing
		String s = "2022-05-26";
		Date d = Date.valueOf(s);
		LocalDate ld1 = LocalDate.parse(s);
		
		String s2 = "2019-03-31";
		Date d2 = Date.valueOf(s2);
		LocalDate ld2 = LocalDate.parse(s2);
		
		List<Transactions> tl2 = new ArrayList<Transactions>();
		Optional<Accounts> a = acctRepo.findById(222222222);
		if(a.isPresent())
		{
			Accounts a1 = a.get();
			List<Transactions> tl = a1.getTrans();
			System.out.println("a is"+a);
			System.out.println("---------------------");
			for(Transactions t: tl)
			{
				System.out.println("Time of transaction "+t.getTransId()+" is "+t.getTransTime());
				System.out.println("Time of transaction "+t.getTransId()+" is "+t.getTransTime());
				System.out.println("the passed date is "+d);
				if((t.getTransTime().isAfter(ld2))&&(t.getTransTime().isBefore(ld2)))
				{
					tl2.add(t);
				}
			}
		
			if (tl2!= null)
			{
				for(Transactions t: tl2)
				{
					System.out.println("t is "+t);
				}	
			}	
			else
			{
				System.out.println("nothing fetched");
			}
		}
		else
		{
			System.out.println("account is not present");
		}
	}
	
	@Autowired
	StatementService stmtServ;
	
	@Test
	public void testService()
	{
		
		String s = "2022-05-26";
		Date d = Date.valueOf(s);
		LocalDate ld1=LocalDate.parse(s);
		
		String s2 = "2019-03-31";
		Date d2 = Date.valueOf(s2);
		LocalDate ld2=LocalDate.parse(s2);
		
		List<Transactions> tl = stmtServ.getCustomStatementEmail(ld2,ld1,"sumisath@email.com");
		Assertions.assertTrue(tl!=null);
		for(Transactions t: tl)
		{
			System.out.println("t is "+t);
		}	
		
	}
}
