package com.sbi.statement.layer4;

import java.awt.print.Pageable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer2.User;
import com.sbi.statement.layer3.AccountsRepository;
import com.sbi.statement.layer3.TransactionsRepository;

@Service
public class StatementServiceImplementation implements StatementService {

	@Autowired
	AccountsRepository acctRepo;

	@Autowired
	TransactionsRepository tranRepo;

	
	@Override
	public List<Transactions> getCustomStatementEmail(LocalDate date1, LocalDate date2, String email) 
			throws AccountsNotFoundException , TransactionsNotFoundException 
	{
		List<Transactions> transList = null;
		Accounts a = new Accounts();
		a.setEmail(email);
		a = acctRepo.findByEmail(email);
		if(a!=null)
		{
			transList = tranRepo.findByTransTimeBetweenAndAcct(date1, date2, a);
			if(transList.size() > 0)
			{
				System.out.println("\t1.size" + transList.size());
			}
			else
			{
				throw new TransactionsNotFoundException("No Transactions found for this account");
			}
		}
		else
		{
			throw new AccountsNotFoundException("This Email Id is not registered");
		}
		return transList;
	}


//	@Override
//	public  List<Transactions> checkLoginService(User user)  
//			throws AccountsNotFoundException, IncorrectPasswordException, TransactionsNotFoundException
//	{
//		List<Transactions> transList1 = new ArrayList<Transactions>();
//		Accounts a = acctRepo.findByEmail(user.getEmail());
//		if(a!=null)
//		{
//			if( user.getPassword().equals(a.getPassword()) )
//				{
//					transList1 = tranRepo.findFirst10ByOrderByTransTimeDesc();
//					if(transList1.size() > 0)
//					{
//						System.out.println("indide transactions: size is "+transList1.size());
//					}
//					else
//					{
//						throw new TransactionsNotFoundException("No Transaction for this account");
//					}
//				}
//			else
//			{
//				throw new IncorrectPasswordException("The password does not match");
//			}
//		}
//		else
//		{
//			throw new AccountsNotFoundException("This Email Id is not registered");
//		}
//		
//		return transList1;
//	}

	@Override
	public  Accounts checkLoginService(User user)  
			throws AccountsNotFoundException, IncorrectPasswordException, TransactionsNotFoundException
	{
		List<Transactions> transList1 = new ArrayList<Transactions>();
		Accounts a = acctRepo.findByEmail(user.getEmail());
		if(a!=null)
		{
			if( user.getPassword().equals(a.getPassword()) )
				{
					transList1 = tranRepo.findFirst10ByOrderByTransTimeDesc();
					if(transList1.size() > 0)
					{
						a.setTrans(transList1);
						System.out.println("indide transactions: size is "+transList1.size());
					}
					else
					{
						throw new TransactionsNotFoundException("No Transaction for this account");
					}
				}
			else
			{
				throw new IncorrectPasswordException("The password does not match");
			}
		}
		else
		{
			throw new AccountsNotFoundException("This Email Id is not registered");
		}
		
		return a;
	}



}
