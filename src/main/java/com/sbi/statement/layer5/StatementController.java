package com.sbi.statement.layer5;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer4.StatementService;

@CrossOrigin
@RestController
public class StatementController {

	@Autowired
	StatementService stmtServ;

//	@GetMapping("/fetch/custom")
//	public List<Transactions> fetchCustomStatement(@RequestParam(name = "acc_no") int accNo,
//			@RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
//			@RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
//		return stmtServ.getCustomStatement(fromDate, toDate, accNo);
//	}
	
//	@GetMapping("/fetch/monthly")
//	public List<Transactions> fetchMonthlyStatementEmail(@RequestParam(name = "acc_no") int accNo,
//			@RequestParam(name = "month") int month,
//			@RequestParam(name = "year") int year) {
//		return stmtServ.getMonthlyStatement(accNo,month,year);
//	}
	
//	@GetMapping("/fetch/custom")
//	public List<Transactions> fetchCustomStatement(@RequestParam(name = "email") String email,
//			@RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
//			@RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
//		return stmtServ.getCustomStatementEmail(fromDate, toDate, email);
//	}
	
	@GetMapping("/fetch/custom/{email}/{start_date}/{end_date}")
	public List<Transactions> fetchCustomStatement(@PathVariable(name = "email") String email,
			@PathVariable(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@PathVariable(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		return stmtServ.getCustomStatementEmail(fromDate, toDate, email);
	}

}
