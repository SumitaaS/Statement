package com.sbi.statement.layer4;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbi.statement.layer2.Transactions;
@Service
public class ServiceLayerImplementation implements StatementService {

	@Override
	public List<Transactions> getMonthlyStatement(int month, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getYearlyStatement(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getCustomStatement(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
